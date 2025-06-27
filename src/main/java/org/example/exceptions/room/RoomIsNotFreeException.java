package org.example.exceptions.room;

public class RoomIsNotFreeException extends RuntimeException {
    public RoomIsNotFreeException(String message) {
        super(message);
    }
}
