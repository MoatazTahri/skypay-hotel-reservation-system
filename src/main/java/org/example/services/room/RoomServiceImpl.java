package org.example.services.room;

import org.example.entities.Room;
import org.example.exceptions.room.RoomAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private final List<Room> rooms = new ArrayList<>();

    @Override
    public void setRoom(Room room) throws RoomAlreadyExistsException {
        if (rooms.stream().anyMatch(r -> r.getNumber() == room.getNumber())) {
            throw new RoomAlreadyExistsException("Room with number " + room.getNumber() + " already exists");
        }
        rooms.add(room);
    }

    @Override
    public boolean isRoomExists(Room room) {
        return rooms.stream().anyMatch(r -> r.getNumber() == room.getNumber());
    }
}
