package riderApp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import riderApp.observer.TripObserver;

public abstract class User implements TripObserver{
    private final String id;
    private final String name;
    private final String contact;
    private final List<Trip>tripHistory;
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getContact() {
        return contact;
    }
    public List<Trip> getTripHistory() {
        return tripHistory;
    }
    public User(String name,String contact){
        this.name=name;
        this.contact=contact;
        this.tripHistory=new ArrayList<>();
        this.id=UUID.randomUUID().toString();
    }
    public void addTrip(Trip trip){
        this.tripHistory.add(trip);
    }
}
