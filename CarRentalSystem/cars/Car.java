package CarRentalSystem.cars;

public class Car{
    private final String vehicleNo;
    private final String carName;
    private final int seats ;
    private final double rentPerDay;
    private final CarType carType;
    private CarStatus carStatus;
    
    public Car(String vehicleNo, String carName, int seats,double rentPerDay,CarType carType) {
        this.vehicleNo = vehicleNo;
        this.carName = carName;
        this.seats = seats;
        this.rentPerDay=rentPerDay;
        this.carType=carType;
        this.carStatus=CarStatus.AVAILABLE;
    }
    public double getRentPerDay() {
        return rentPerDay;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getCarName() {
        return carName;
    }

    public int numberOfSeats() {
        return seats;
    }

    public CarStatus geCarStatus() {
        return carStatus;
    }
  
    public void setCarStatus(CarStatus carStatus) {
        this.carStatus = carStatus;
    }
    public CarType getCarType() {
        return carType;
    }

   
}
