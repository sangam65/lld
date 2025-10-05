package HotelManagemnet.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class Reservation {
    private final Customer customer;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String reservationId;
    private final double rent;
    private final Room room;
    
    public Reservation(Customer customer, LocalDate startDate, LocalDate endDate, Room room) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reservationId = UUID.randomUUID().toString();
        this.room=room;
        this.rent = calculateRent();
    }
    private double calculateRent(){
        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        double price=days*room.getRentPerDay();
        return price;

    }
    public Customer getCustomer() {
        return customer;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public String getReservationId() {
        return reservationId;
    }
    public double getRent() {
        return rent;
    }
    public Room getRoom() {
        return room;
    }
}
