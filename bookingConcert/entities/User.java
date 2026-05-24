package bookingConcert.entities;

import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

public class User {
    private final String userId;
    private final String username;
    private final List<Booking> bookings;
    public String getUserId() {
        return userId;
    }
    public String getUsername() {
        return username;
    }
    public  List<Booking> getBookings() {
        return bookings;
    }
    public User(String username) {
        this.username = username;
        this.bookings=new ArrayList<>();
        this.userId=UUID.randomUUID().toString();
    }
    public synchronized  boolean addBooking(Booking booking){
        bookings.add(booking);
        return true;
    }
    
}
