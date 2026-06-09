package riderApp.observer;

import riderApp.entities.Trip;

public interface TripObserver {
    void onUpdateTrip(Trip trip);
}
