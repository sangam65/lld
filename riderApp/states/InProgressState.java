package riderApp.states;

import riderApp.entities.Driver;
import riderApp.entities.Trip;
import riderApp.enums.TripStatus;

public class InProgressState implements TripState{

    @Override
    public void assign(Trip trip, Driver driver) {
       System.out.println("Cannot assign a new driver while trip is in progress.");
        
    }

    @Override
    public void end(Trip trip) {
        trip.setTripStatus(TripStatus.COMPLETED);
        trip.updateTripState(new CompletedState());
        
    }

    @Override
    public void request(Trip trip) {
       System.out.println("Trip is already in progress.");
        
    }

    @Override
    public void start(Trip trip) {
      System.out.println("Trip is already in progress.");
        
    }

}
