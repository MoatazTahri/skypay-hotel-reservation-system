package org.example;

import org.example.entities.BookingRequest;
import org.example.entities.Room;
import org.example.entities.User;
import org.example.enumerations.RoomType;
import org.example.services.booking.BookingService;
import org.example.services.booking.BookingServiceImpl;
import org.example.services.room.RoomService;
import org.example.services.room.RoomServiceImpl;
import org.example.services.user.UserService;
import org.example.services.user.UserServiceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

    private static void trySetUser(UserService userService, User user) {
        try {
            userService.setUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void trySetRoom(RoomService roomService, Room room) {
        try {
            roomService.setRoom(room);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void tryBookRoom(BookingService bookingService, BookingRequest bookingRequest) {
        try {
            bookingService.bookRoom(bookingRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        User user1 = new User(1, 5000);
        User user2 = new User(2, 10000);
        Room room1 = new Room(1, RoomType.STANDARD, 1000);
        Room room2 = new Room(2, RoomType.JUNIOR, 2000);
        Room room3 = new Room(3, RoomType.MASTER, 3000);
        UserService userService = new UserServiceImpl();
        RoomService roomService = new RoomServiceImpl();
        BookingService bookingService = new BookingServiceImpl(userService, roomService);
        trySetUser(userService, user1);
        trySetUser(userService, user2);
        trySetRoom(roomService, room1);
        trySetRoom(roomService, room2);
        trySetRoom(roomService, room3);
        BookingRequest bookingRequest1 = new BookingRequest(user1, room2, dateFormat.parse("30/06/2026"), dateFormat.parse("07/07/2026"));
        BookingRequest bookingRequest2 = new BookingRequest(user1, room2, dateFormat.parse("30/30/2026"), dateFormat.parse("07/07/2026"));
        BookingRequest bookingRequest3 = new BookingRequest(user1, room1, dateFormat.parse("07/07/2026"), dateFormat.parse("08/07/2026"));
        BookingRequest bookingRequest4 = new BookingRequest(user2, room1, dateFormat.parse("07/07/2026"), dateFormat.parse("09/07/2026"));
        BookingRequest bookingRequest5 = new BookingRequest(user2, room3, dateFormat.parse("07/07/2026"), dateFormat.parse("08/07/2026"));
        tryBookRoom(bookingService, bookingRequest1);
        tryBookRoom(bookingService, bookingRequest2);
        tryBookRoom(bookingService, bookingRequest3);
        tryBookRoom(bookingService, bookingRequest4);
        tryBookRoom(bookingService, bookingRequest5);
        trySetRoom(roomService, new Room(1, RoomType.MASTER, 10000));
        userService.printAllUsers();
        System.out.println("------------------------------------------------------------");
        bookingService.printAll();
    }
}