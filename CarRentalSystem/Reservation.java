package CarRentalSystem;

import java.time.LocalDate;
import java.util.UUID;

import CarRentalSystem.cars.Car;

public class Reservation {
    private final String reservationId;
    private final Car car;
    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    public Reservation( Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.reservationId =UUID.randomUUID().toString();
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = calculateTotalRent();
    }
    private double calculateTotalRent(){
        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        
        return days * this.car.getRentPerDay();

    }
    public String getReservationId() {
        return reservationId;
    }
    public Car getCar() {
        return car;
    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
}
