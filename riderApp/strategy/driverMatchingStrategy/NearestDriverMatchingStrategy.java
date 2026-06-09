package riderApp.strategy.driverMatchingStrategy;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import riderApp.entities.Driver;
import riderApp.entities.Location;
import riderApp.enums.DriverStatus;
import riderApp.enums.RideType;

public class NearestDriverMatchingStrategy implements DriverMatchingStrategy {
    private static final double MAX_DISTANCE_KM = 5.0; // Max distance to consider a driver "nearby"

    @Override
    public List<Driver> findDrivers(List<Driver> allDrivers, Location pickupLocation, RideType rideType) {
        System.out.println("Finding nearest drivers for ride type: " + rideType);
        return allDrivers.stream()
                .filter(driver -> driver.getStatus() == DriverStatus.ONLINE)
                .filter(driver -> driver.getVehcile() .getRideType()== rideType)
                .filter(driver -> pickupLocation.getDistance(driver.getLocation()) <= MAX_DISTANCE_KM)
                .sorted(Comparator.comparingDouble(driver -> pickupLocation.getDistance(driver.getLocation())))
                .collect(Collectors.toList());
    }
}
