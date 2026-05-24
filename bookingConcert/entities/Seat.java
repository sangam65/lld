package bookingConcert.entities;

import bookingConcert.enums.SeatStatus;
import bookingConcert.enums.SeatType;

public class Seat {
    private final int seatNumber;
    private  SeatStatus seatStatus;
    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
    private final SeatType seatType;
    public int getSeatNumber() {
        return seatNumber;
    }
    public SeatStatus getSeatStatus() {
        return seatStatus;
    }
    public SeatType getSeatType() {
        return seatType;
    }
    public Seat(int seatNumber, SeatType seatType) {
        this.seatNumber = seatNumber;
        this.seatStatus = SeatStatus.EMPTY;
        this.seatType = seatType;
    }
    
}
