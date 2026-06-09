package riderApp.strategy.pricingStrategy;



import riderApp.entities.Location;
import riderApp.enums.RideType;

public interface PricingStrategy {
    double calculateFare(Location pickup, Location dropoff, RideType rideType);
}