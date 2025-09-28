package CarRentalSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import CarRentalSystem.cars.Car;
import CarRentalSystem.cars.CarStatus;
import CarRentalSystem.exception.CarException;
import CarRentalSystem.exception.CustomerException;
import CarRentalSystem.exception.ReservationException;
import CarRentalSystem.payment.PaymentStrategy;
import CarRentalSystem.payment.UPIStratgey;

public class CarManagement {
    private ConcurrentHashMap<String, Car> cars;
    private ConcurrentHashMap<String, Reservation> reservations;
    private PaymentStrategy paymentStrategy;
    private static  CarManagement carManagement;

    private CarManagement() {
        this.cars = new ConcurrentHashMap<>();
        this.reservations = new ConcurrentHashMap<>();
        this.paymentStrategy = new UPIStratgey();
    }
    public static CarManagement getCarManagement (){
        if(carManagement==null){
            synchronized(CarManagement.class){
                if(carManagement==null){
                    carManagement=new CarManagement();
                }
            }
        }
        return carManagement;
    }

    public synchronized void addCar(Car car) throws CarException {
        if (cars.containsKey(car.getVehicleNo())) {
            throw new CarException("car alread added to the list");
        }
        cars.put(car.getVehicleNo(), car);
    }

    public synchronized void removeCar(Car car)throws CarException{
        if (!cars.containsKey(car.getVehicleNo())) {
            throw new CarException("car does not exist");
        }
        for(Reservation reservation:reservations.values()){
            if(reservation.getCar().equals(car)){
                throw new CarException("this car is booked , so can't be removed");
            }
        }
        cars.remove(car.getVehicleNo());
    }
    private boolean isCarAvailable(Car car,LocalDate starDate){
        for(Reservation reservation:reservations.values()){
            if(reservation.getCar().equals(car)){
                if(starDate.isAfter(reservation.getEndDate())){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
    public synchronized Reservation makeReservation(Car car,Customer customer,LocalDate starDate,LocalDate endDate) throws CarException {
        
        if(!endDate.isAfter(starDate)){
            throw new CustomerException("start date is after end date");
        }
        
        if (!cars.containsKey(car.getVehicleNo())) {
            throw new CarException("given car does not exist");
        }
        if(!isCarAvailable(car, starDate)){
            throw new CarException("this car is booked between given dates");
        }
        car.setCarStatus(CarStatus.BOOKED);
        Reservation reservation=new Reservation(car, customer, starDate, endDate);
        
        reservations.put(reservation.getReservationId(), reservation);
        
        return reservation;
    }

    public boolean processPayment(Reservation reservation){
        System.out.println(reservation.getTotalPrice());
        paymentStrategy.processPayment();

        return true;
    }
    public synchronized void cancelReservation(String reservationId)throws ReservationException{
        if(!reservations.containsKey(reservationId)){
            throw new ReservationException("The reservation does not exist for given reservationId");
        }
        Reservation reservation=reservations.get(reservationId);
        reservation.getCar().setCarStatus(CarStatus.AVAILABLE);
        reservations.remove(reservationId);
    }

    public List<Car> getCars(String carType,LocalDate startDate,LocalDate endDate){
            List<Car> carList=new ArrayList<>();
            for(Car car:cars.values()){
                if(car.getCarName().equals(carType) && isCarAvailable(car, endDate)){
                    carList.add(car);
                }
            }
            return carList;
    }

   

}
