package riderApp.strategy.driverMatchingStrategy;


import java.util.List;

import riderApp.entities.Driver;
import riderApp.entities.Location;
import riderApp.enums.RideType;

public interface DriverMatchingStrategy {
    List<Driver> findDrivers(List<Driver> allDrivers, Location pickupLocation, RideType rideType);
}
