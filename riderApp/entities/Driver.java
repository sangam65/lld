package riderApp.entities;

import riderApp.enums.DriverStatus;

public class Driver extends User{
    private Location location;
    private Vehcile vehcile;
    private DriverStatus driverStatus;
    public Location getLocation() {
        return location;
    }
    public void updateLocation(Location location) {
        this.location = location;
    }
    public Vehcile getVehcile() {
        return vehcile;
    }
   
    public DriverStatus getStatus() {
        return driverStatus;
    }
    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
        System.out.println("Driver "+getName()+" is now "+driverStatus);
    }
    public Driver(String name,String contact,Location location,Vehcile vehcile){
        super(name,contact);
        this.location=location;
        this.vehcile=vehcile;
        this.driverStatus=DriverStatus.OFFLINE;
    }
    @Override
    public void onUpdateTrip(Trip trip) {
        System.out.printf("Notification for driver %s \n",getName());
        // System.out.printf("Trip status %s \n",trip)
    }
}
