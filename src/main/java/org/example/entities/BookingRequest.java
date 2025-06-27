package org.example.entities;

import lombok.Getter;
import org.example.enumerations.RoomType;

import java.util.Date;

@Getter
public class BookingRequest {
    private final User user;
    private final int roomNumber;
    private final RoomType type;
    private final int pricePerNight;
    private final Date checkIn;
    private final Date checkOut;

    public BookingRequest(User user, Room room, Date checkIn, Date checkOut) {
        this.user = user;
        this.roomNumber = room.getNumber();
        this.type = room.getType();
        this.pricePerNight = room.getPricePerNight();
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }
}
