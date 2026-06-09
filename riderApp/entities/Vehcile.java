package riderApp.entities;

import riderApp.enums.RideType;

public class Vehcile {
    private final String model;
    private final String licenseNumber;
    private final RideType rideType;
    public String getModel() {
        return model;
    }
    public String getLicenseNumber() {
        return licenseNumber;
    }
    public RideType getRideType() {
        return rideType;
    }
    public Vehcile(String model, String licenseNumber, RideType rideType) {
        this.model = model;
        this.licenseNumber = licenseNumber;
        this.rideType = rideType;
    }
}
