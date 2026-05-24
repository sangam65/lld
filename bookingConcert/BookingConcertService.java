package bookingConcert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import bookingConcert.entities.Booking;
import bookingConcert.entities.Concert;
import bookingConcert.entities.Seat;
import bookingConcert.entities.User;
import bookingConcert.enums.SeatType;
import bookingConcert.exception.BookingConcertException;

public class BookingConcertService {
    private final Map<String,Concert>concerts=new ConcurrentHashMap<>();
    private final Map<String,User>users=new ConcurrentHashMap<>();
    private static BookingConcertService bookingConcertService;
    private BookingConcertService(){

    }
    public synchronized static BookingConcertService gBookingConcertServiceInstance(){
        if(bookingConcertService==null){
            bookingConcertService=new BookingConcertService();
        }
        return bookingConcertService;
    }
    public boolean addConcert(String concertName,LocalDateTime startTime,LocalDateTime endTime,String place) throws BookingConcertException{
        if(concerts.containsKey(concertName)){
            throw new BookingConcertException("Concert with same name exists");
        }
        Concert concert=new Concert(concertName, startTime, endTime);
        concerts.put(concertName,concert);
        return true;
    }
    public boolean addUser(String username) throws BookingConcertException{
        if(users.containsKey(username)){
            throw new BookingConcertException("User with same name exists");
        }
        User user=new User(username);
        users.put(username, user);
        return true;
    }
    public List<Seat> getSeatsAvailable(String concertName) throws InterruptedException{
         if(!concerts.containsKey(concertName)){
            throw new BookingConcertException("Concert does not exist");
        }
        return concerts.get(concertName).getAvailableSeats();
    }
    public synchronized Booking bookSeatsForConcert(String userName,int totalSeats,SeatType seatType,String concertName)throws InterruptedException,BookingConcertException{
          if(!concerts.containsKey(concertName)){
            throw new BookingConcertException("Concert does not exist");

        }
        if(!users.containsKey(userName)){
            throw new BookingConcertException("User with given name don't exists");
        }
        User user=users.get(userName);
        Concert concert=concerts.get(concertName);
        Booking booking= concert.getSeatManagement().bookSeats(concert.getConcertId(), user.getUserId(), totalSeats, seatType);
        user.addBooking(booking);
        return booking;
    }
    
}
