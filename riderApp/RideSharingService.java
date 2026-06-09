package riderApp;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentHashMap;

import riderApp.entities.Driver;
import riderApp.entities.Location;
import riderApp.entities.Trip;
import riderApp.entities.Vehcile;
import riderApp.enums.DriverStatus;
import riderApp.enums.RideType;
import riderApp.observer.Rider;
import riderApp.strategy.driverMatchingStrategy.DriverMatchingStrategy;
import riderApp.strategy.pricingStrategy.PricingStrategy;

public class RideSharingService {
    private static volatile RideSharingService instance;
    private final Map<String, Rider> riders = new ConcurrentHashMap<>();
    private final Map<String, Driver> drivers = new ConcurrentHashMap<>();
    private final Map<String, Trip> trips = new ConcurrentHashMap<>();
    private PricingStrategy pricingStrategy;
    private DriverMatchingStrategy driverMatchingStrategy;

    private RideSharingService() {}

    public static synchronized RideSharingService getInstance() {
        if (instance == null) {
            instance = new RideSharingService();
        }
        return instance;
    }

    // Allow changing strategies at runtime for extensibility
    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setDriverMatchingStrategy(DriverMatchingStrategy driverMatchingStrategy) {
        this.driverMatchingStrategy = driverMatchingStrategy;
    }

    public Rider registerRider(String name, String contact) {
        Rider rider = new Rider(name, contact);
        riders.put(rider.getId(), rider);
        return rider;
    }

    public Driver registerDriver(String name, String contact, Vehcile vehicle, Location initialLocation) {
        Driver driver = new Driver(name, contact, initialLocation,vehicle);
        drivers.put(driver.getId(), driver);
        return driver;
    }

    public Trip requestRide(String riderId, Location pickup, Location dropoff, RideType rideType) {
        Rider rider = riders.get(riderId);
        if (rider == null)
            throw new NoSuchElementException("Rider not found");

        System.out.println("\n--- New Ride Request from " + rider.getName() + " ---");

        // 1. Find available drivers
        List<Driver> availableDrivers = driverMatchingStrategy.findDrivers(List.copyOf(drivers.values()), pickup, rideType);

        if (availableDrivers.isEmpty()) {
            System.out.println("No drivers available for your request. Please try again later.");
            return null;
        }

        System.out.println("Found " + availableDrivers.size() + " available driver(s).");

        // 2. Calculate fare
        double fare = pricingStrategy.calculateFare(pickup, dropoff, rideType);
        System.out.printf("Estimated fare: $%.2f%n", fare);

        // 3. Create a trip using the Builder
        Trip trip = new Trip.TripBuilder()
                .setRider(rider)
                .pickUpLocation(pickup)
                .destLocation(dropoff)
                .fair(fare)
                .build();

        trips.put(trip.getTripId(), trip);

        // 4. Notify nearby drivers (in a real system, this would be a push notification)
        System.out.println("Notifying nearby drivers of the new ride request...");
        for (Driver driver : availableDrivers) {
            System.out.println(" > Notifying " + driver.getName() + " at " + driver.getLocation());
            driver.onUpdateTrip(trip);
        }

        return trip;
    }

    public void acceptRide(String driverId, String tripId) {
        Driver driver = drivers.get(driverId);
        Trip trip = trips.get(tripId);
        if (driver == null || trip == null)
            throw new NoSuchElementException("Driver or Trip not found");

        System.out.println("\n--- Driver " + driver.getName() + " accepted the ride ---");

        driver.setDriverStatus(DriverStatus.IN_TRIP);
        trip.assignDriver(driver);
    }

    public void startTrip(String tripId) {
        Trip trip = trips.get(tripId);
        if (trip == null)
            throw new NoSuchElementException("Trip not found");
        System.out.println("\n--- Trip " + trip.getTripId() + " is starting ---");
        trip.startTrip();
    }

    public void endTrip(String tripId) {
        Trip trip = trips.get(tripId);
        if (trip == null)
            throw new NoSuchElementException("Trip not found");
        System.out.println("\n--- Trip " + trip.getTripId() + " is ending ---");
        trip.endTrip();

        // Update statuses and history
        Driver driver = trip.getDriver();
        driver.setDriverStatus(DriverStatus.ONLINE); // Driver is available again
        driver.updateLocation(trip.getdropUpLocation()); // Update driver location

        Rider rider = trip.getRider();
        driver.addTrip(trip);
        rider.addTrip(trip);

        System.out.println("Driver " + driver.getName() + " is now back online at " + driver.getLocation());
    }
}