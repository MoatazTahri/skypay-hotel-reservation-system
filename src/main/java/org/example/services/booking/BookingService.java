package org.example.services.booking;

import org.example.entities.BookingRequest;

public interface BookingService {
    void bookRoom(BookingRequest bookingRequest);
    void printAll();
}
