package org.example.services.room;

import org.example.entities.Room;

public interface RoomService {
    void setRoom(Room room);
    boolean isRoomExists(int roomNumber);
}
