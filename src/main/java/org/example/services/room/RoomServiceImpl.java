package org.example.services.room;

import org.example.entities.Room;
import org.example.exceptions.room.RoomAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final List<Room> rooms = new ArrayList<>();

    @Override
    public void setRoom(Room room) throws RoomAlreadyExistsException {
        Room room1 = rooms.stream().filter(r -> r.getNumber() == room.getNumber()).findFirst().orElse(null);
        if (room1 != null) {
            room1.setType(room.getType());
            room1.setPricePerNight(room.getPricePerNight());
        } else {
            rooms.add(room);
        }
    }

    @Override
    public boolean isRoomExists(int roomNumber) {
        return rooms.stream().anyMatch(r -> r.getNumber() == roomNumber);
    }
}
