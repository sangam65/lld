package bookingConcert.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final String bookingId;
    private final String userId;
    private final LocalDateTime dateTime;
    private final List<Seat>seats;
    private final double price;
    private final String concerttId;
    public String getBookingId() {
        return bookingId;
    }
    public String getUserId() {
        return userId;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public double getPrice() {
        return price;
    }
    public String getConcerttId() {
        return concerttId;
    }
    public Booking(String userId, LocalDateTime dateTime, List<Seat> seats, double price, String concerttId) {
        this.userId = userId;
        this.dateTime = dateTime;
        this.seats = seats;
        this.price = price;
        this.concerttId = concerttId;
        this.bookingId=UUID.randomUUID().toString();
    }
}
