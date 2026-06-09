package riderApp.states;

import riderApp.entities.Driver;
import riderApp.entities.Trip;
import riderApp.enums.TripStatus;

public class Requestedstate implements TripState{

    @Override
    public void request(Trip trip) {
        System.out.println("Trip is already in requested state");
    }

    @Override
    public void assign(Trip trip, Driver driver) {

       trip.setDriver(driver);
       trip.setTripStatus(TripStatus.ASSIGNED);
       trip.updateTripState(new AssignedState());
    }

    @Override
    public void start(Trip trip) {
        System.out.println("Cannot start a trip that has not been assigned a driver.");
    }

    @Override
    public void end(Trip trip) {
         System.out.println("Cannot end a trip that has not been assigned a driver.");
      
    }

}
