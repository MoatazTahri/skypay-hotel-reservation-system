package org.example.services.booking;

import org.example.entities.BookingRequest;
import org.example.entities.Room;
import org.example.entities.User;
import org.example.exceptions.room.RoomIsNotFreeException;
import org.example.exceptions.room.RoomNotFoundException;
import org.example.exceptions.user.UserInsufficientBalanceException;
import org.example.exceptions.user.UserNotFoundException;
import org.example.services.room.RoomService;
import org.example.services.user.UserService;
import org.example.utils.TimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class BookingServiceImpl implements BookingService {
    private final List<BookingRequest> bookingRequests = new ArrayList<>();
    private final UserService userService;
    private final RoomService roomService;

    public BookingServiceImpl(UserService userService, RoomService roomService) {
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public void bookRoom(BookingRequest bookingRequest) {
        User user = bookingRequest.getUser();
        Room room = bookingRequest.getRoom();
        // Check for user existence
        if (!userService.isUserExists(user)) {
            throw new UserNotFoundException("Cannot book room number {" + room.getNumber() + "} for user id {" + user.getId() + "} : user not found");
        }
        // Check for room existence
        if (!roomService.isRoomExists(room)) {
            throw new RoomNotFoundException("Cannot book room with number {" + room.getNumber() + "} for user id {" + user.getId() + "} : room not found");
        }
        // Check for dates nullability
        if (bookingRequest.getCheckIn() == null || bookingRequest.getCheckOut() == null) {
            throw new IllegalArgumentException("Cannot book room number {" + room.getNumber() + "} for user id {" + user.getId() + "} : check in or check out date is null");
        }
        // Check if end date is after start date
        if (bookingRequest.getCheckIn().after(bookingRequest.getCheckOut())) {
            throw new IllegalArgumentException("Cannot book room number {" + room.getNumber() + "} for user id {" + user.getId() + "} : check in date is after check out date");
        }
        // Check if the balance is enough for booking
        int bookingDurationInDays = TimeUtils.getDaysBetween(bookingRequest.getCheckIn(), bookingRequest.getCheckOut());
        if (bookingRequest.getUser().getBalance() < bookingRequest.getRoom().getPricePerNight() * bookingDurationInDays) {
            throw new UserInsufficientBalanceException("Cannot book room number {" + room.getNumber() + "} for user id {" + user.getId() + "} : insufficient balance for " + bookingDurationInDays + " night(s)");
        }
        // Check for room availability
        List<BookingRequest> alreadyBookedRooms = bookingRequests.stream()
                .filter(b -> b.getRoom().getNumber() == room.getNumber()
                        && bookingRequest.getCheckIn().before(b.getCheckOut()))
                .toList();
        if (!alreadyBookedRooms.isEmpty()) {
            throw new RoomIsNotFreeException("Cannot book room with number {" + room.getNumber() + "} for user id {" + user.getId() + "} : room is not free");
        }
        bookingRequests.add(bookingRequest);
        userService.getUserById(user.getId()).setBalance(user.getBalance() - bookingRequest.getRoom().getPricePerNight() * bookingDurationInDays);
    }

    @Override
    public void printAll() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        ListIterator<BookingRequest> bookingsIterator = bookingRequests.listIterator(bookingRequests.size());
        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s%n",
                "User ID", "Room #", "Type", "Price/Night", "Check-In", "Check-Out");
        // Printing bookings from latest to oldest
        while (bookingsIterator.hasPrevious()) {
            BookingRequest booking = bookingsIterator.previous();
            System.out.printf("%-10d %-10d %-10s %-15d %-15s %-15s%n",
                    booking.getUser().getId(),
                    booking.getRoom().getNumber(),
                    booking.getRoom().getType(),
                    booking.getRoom().getPricePerNight(),
                    dateFormat.format(booking.getCheckIn()),
                    dateFormat.format(booking.getCheckOut()));
        }
    }
}
