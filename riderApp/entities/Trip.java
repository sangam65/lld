package riderApp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import riderApp.enums.TripStatus;
import riderApp.observer.Rider;
import riderApp.observer.TripObserver;
import riderApp.states.Requestedstate;
import riderApp.states.TripState;

public class Trip {
    private final String tripId;
    private final Location pickUpLocation;
    private final Location dropUpLocation;
    private Driver driver;
    private final Rider rider;
    private final double fair;
    private TripStatus tripStatus;
    private TripState tripState;
    private final List<TripObserver>tripObservers;
    
    public void addObserver(TripObserver tripObserver){
        tripObservers.add(tripObserver);
    }
    private void notifyObservers(){
        this.tripObservers.forEach((o)->o.onUpdateTrip(this));
    }
    public double getFair() {
        return fair;
    }
    public void assignDriver(Driver driver){
        tripState.assign(this, driver);
        addObserver(driver);
        notifyObservers();
    }
    public void startTrip() {
        tripState.start(this);
        notifyObservers();
    }

    public void endTrip() {
        tripState.end(this);
        notifyObservers();
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }
    public void setTripStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public TripState getTripState() {
        return tripState;
    }

    public void updateTripState(TripState tripState) {
        this.tripState = tripState;
    }

    public Location getpickUpLocation() {
        return pickUpLocation;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Location getdropUpLocation() {
        return dropUpLocation;
    }

    public Driver getDriver() {
        return driver;
    }

    public Rider getRider() {
        return rider;
    }

    private Trip(TripBuilder tripBuilder) {
                this.tripId=tripBuilder.tripId;
        this.pickUpLocation = tripBuilder.pickUpLocation;
        this.dropUpLocation =tripBuilder. dropUpLocation;     
        this.fair=tripBuilder.fair;
        this.rider =tripBuilder. rider;
        this.tripObservers=new ArrayList<>();
        this.tripStatus=TripStatus.REQUESTED;
        this.tripState=new Requestedstate();

    }

    public static class TripBuilder {
        private final String tripId;
        private Rider rider;
        private Location pickUpLocation;
        private Location dropUpLocation;
        private double fair;

        public TripBuilder() {
            this.tripId = UUID.randomUUID().toString();
        }
        public TripBuilder pickUpLocation(Location pickUpLocation){
            this.pickUpLocation=pickUpLocation;
            return this;
        }
        public TripBuilder destLocation(Location dropUpLocation){
            this.dropUpLocation=dropUpLocation;
            return this;
        }
        public TripBuilder setRider(Rider rider){
            this.rider=rider;
            return this;
        }
        public TripBuilder fair(double fair){
            this.fair=fair;
            return this;
        }
        public Trip build(){
            return new Trip(this);
        }
    }

    public void updateStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }
    public String getTripId() {
        return tripId;
    }
}
