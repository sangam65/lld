package HotelManagemnet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import HotelManagemnet.entities.Customer;
import HotelManagemnet.entities.Reservation;
import HotelManagemnet.entities.Room;
import HotelManagemnet.enums.RoomStatus;
import HotelManagemnet.enums.RoomType;
import HotelManagemnet.exception.ReservationException;
import HotelManagemnet.exception.RoomException;
import HotelManagemnet.payment.PaymentStrategy;
import HotelManagemnet.payment.UPIPaymentStrategy;

public class HotelManagement {
    public static volatile HotelManagement hotelManagement = null;

    private ConcurrentHashMap<String, Room> rooms;
    private ConcurrentHashMap<String, Reservation> reservations;
    private PaymentStrategy paymentStrategy;

    private HotelManagement() {
        rooms = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        paymentStrategy = new UPIPaymentStrategy();
    }

    public synchronized void addRoom(Room room) throws RoomException {
        if (rooms.containsKey(room.getRoomId())) {
            throw new RoomException("room is already added in hotel");
        }
        rooms.put(room.getRoomId(), room);
    }

    private Room roomAvailable(LocalDate startDate, LocalDate endDate, RoomType roomType) {

        for (Room room : rooms.values()) {
            if (!room.getRoomType().equals(roomType) ) {
                continue;
            }
            boolean hasConflict = false;
            for (Reservation reservation : reservations.values()) {
                Room reservedRoom = reservation.getRoom();
                if (reservedRoom.getRoomId().equals(room.getRoomId())
                        && datesOverlap(startDate, endDate, reservation.getStartDate(), reservation.getEndDate())) {
                    hasConflict = true;
                    break;
                }
            }
            if (hasConflict == false) {
                return room;
            }
        }

        return null;

    }

    public synchronized Reservation bookRoom(Customer customer, LocalDate startDate, LocalDate endDate,
            RoomType roomType) throws RoomException, ReservationException {
        if (startDate.isAfter(endDate)) {
            throw new ReservationException("Start date cannot be after end date");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new ReservationException("Cannot book rooms for past dates");
        }
        Room room = roomAvailable(startDate, endDate, roomType);
        if (room == null) {
            throw new RoomException("Room not found for given dates for roomtype " + roomType.toString());
        }
        try {
            Reservation reservation = new Reservation(customer, startDate, endDate, room);
            paymentStrategy.processPayment(reservation.getRent());
            reservations.put(reservation.getReservationId(), reservation);

            room.setRoomStatus(RoomStatus.BOOKED);
            return reservation;
        } catch (ReservationException e) {
            throw new ReservationException("failed to reserve room as " + e.getMessage());
        }

    }

    public static HotelManagement getInstance() {
        if (hotelManagement == null) {
            synchronized (HotelManagement.class) {
                if (hotelManagement == null) {
                    hotelManagement = new HotelManagement();
                }
            }
        }
        return hotelManagement;
    }

    public synchronized boolean cancelReservation(String reservationId) throws ReservationException {
        if (!reservations.containsKey(reservationId)) {
            throw new ReservationException("the reservationId does not exists");
        }

        Reservation reservation = reservations.get(reservationId);
        paymentStrategy.refundPayment(reservation.getRent());
        reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
        reservations.remove(reservationId);
        return true;
    }

    // this will be private method where we will run a scheduler and empty those
    // rooms whose endDate is today and make those roomAvailable
    @SuppressWarnings("unused")
    private synchronized void emptyRoom() {
        List<String> removeReservationId = new ArrayList<>();
        for (Reservation reservation : reservations.values()) {
            LocalDate endDate = reservation.getEndDate();
            LocalDate curDate = LocalDate.now();
            int today = endDate.compareTo(curDate);
            if (today == 0) {
                reservation.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
                removeReservationId.add(reservation.getReservationId());

            }
        }
        for (String reservationId : removeReservationId) {
            reservations.remove(reservationId);
        }
    }

    private boolean datesOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {

       return start1.isBefore(end2) && start2.isBefore(end1);
    }

    public List<Room> searchRooms(RoomType roomType, LocalDate startDate, LocalDate endDate) {
        List<Room> roomList = new ArrayList<>();
        for (Room room : rooms.values()) {
            if (!room.getRoomType().equals(roomType))
                continue;
            boolean roomAvailable = true;
            for (Reservation reservation : reservations.values()) {

                if (reservation.getRoom().getRoomId().equals(room.getRoomId())
                        && datesOverlap(startDate, endDate, reservation.getStartDate(), reservation.getEndDate())) {
                    roomAvailable = false;
                    break;
                }

            }
            if (roomAvailable == true) {
                roomList.add(room);
            }
        }

        return roomList;
    }
}
