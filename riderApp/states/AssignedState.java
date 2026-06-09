package riderApp.states;

import riderApp.entities.Driver;
import riderApp.entities.Trip;
import riderApp.enums.TripStatus;

public class AssignedState  implements TripState{

    @Override
    public void request(Trip trip) {
       System.out.println("Trip is already requested and Driver is already assigned  ");
    }

    @Override
    public void assign(Trip trip, Driver driver) {
         System.out.println("Trip is already assigned. To re-assign, cancel first.");
    }

    @Override
    public void start(Trip trip) {
        trip.setTripStatus(TripStatus.IN_PROGRESS);
        trip.updateTripState(new InProgressState());
    }

    @Override
    public void end(Trip trip) {
          System.out.println("Cannot end a trip that has not started.");
    }

}
