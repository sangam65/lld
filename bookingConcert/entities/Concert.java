package bookingConcert.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Concert {
    private final String concertId;
    private final String name;
    private final LocalDateTime starTime;
    private final LocalDateTime endTime;
    private final SeatManagement seatManagement;
    public String getConcertId() {
        return concertId;
    }
    public String getName() {
        return name;
    }
    public LocalDateTime getStarTime() {
        return starTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public SeatManagement getSeatManagement() {
        return seatManagement;
    }
    public Concert(String name, LocalDateTime starTime, LocalDateTime endTime) {
        this.name = name;
        this.starTime = starTime;
        this.endTime = endTime;
        this.seatManagement = new SeatManagement();
        this.concertId=UUID.randomUUID().toString();
    }
    public List<Seat> getAvailableSeats() throws InterruptedException{
        return seatManagement.available();
    }
    
}
