package bookingConcert.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import bookingConcert.enums.SeatStatus;
import bookingConcert.enums.SeatType;
import bookingConcert.exception.BookingConcertException;

public class SeatManagement {
    private final Seat[] seats;
    private final int totalSeats;
    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(true);

    public int getTotalSeats() {
        return totalSeats;
    }

    public SeatManagement() {
        this.seats = new Seat[100];
        this.totalSeats = 100;
        for (int i = 0; i < 100; i++) {
            SeatType seatType;
            if (i < 50)
                seatType = SeatType.ECONOMY;
            else if (i < 80)
                seatType = SeatType.STANDARD;
            else
                seatType = SeatType.VIP;
            this.seats[i] = new Seat(i + 1, seatType);
        }
    }

    public  Booking bookSeats(String concertId, String userId, int totalSeats, SeatType seatType)
            throws InterruptedException, BookingConcertException {
        if (!reentrantReadWriteLock.writeLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new BookingConcertException("Try again later");
        }
        try {
            List<Seat> emptySeats = Arrays.stream(seats)
                    .filter((s) -> s.getSeatType().equals(seatType) && s.getSeatStatus().equals(SeatStatus.EMPTY))
                    .toList();
            if (emptySeats.size() < totalSeats) {
                throw new BookingConcertException("Only " + emptySeats.size() + " availabke under given type");
            }
            List<Seat>bookedSeats=new ArrayList<>();
            for(int i=0;i<totalSeats;i++){
                emptySeats.get(i).setSeatStatus(SeatStatus.BOOKED);
                bookedSeats.add(emptySeats.get(i));
            }
            
            Booking booking = new Booking(userId, LocalDateTime.now(), bookedSeats, totalSeats * seatType.getPrice(),
                    concertId);
            return booking;
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public List<Seat> available() throws InterruptedException {
        if (!reentrantReadWriteLock.readLock().tryLock(2000, TimeUnit.MILLISECONDS)) {
            throw new BookingConcertException("Try again later");
        }
        try {
            List<Seat> emptySeats = Arrays.stream(seats)
                    .filter((s) -> s.getSeatStatus().equals(SeatStatus.EMPTY))
                    .toList();
            return emptySeats;
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }

    }
}
