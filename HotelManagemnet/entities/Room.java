package HotelManagemnet.entities;

import java.util.UUID;

import HotelManagemnet.enums.RoomStatus;
import HotelManagemnet.enums.RoomType;

public class Room {
    private final String roomId;
    private final double rentPerDay;
    private RoomStatus roomStatus;
    private RoomType roomType;

    public Room(RoomStatus roomStatus,RoomType roomType,double rentPerDay){
        this.roomId=UUID.randomUUID().toString();
        this.roomStatus=roomStatus;
        this.rentPerDay=rentPerDay;
        this.roomType=roomType;
    }   
    public double getRentPerDay() {
        return rentPerDay;
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

}
