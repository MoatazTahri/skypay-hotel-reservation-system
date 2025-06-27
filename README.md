# 🏨 Hotel Reservation System

This is a simple, modular Hotel Reservation System built with Java, following clean code principles, layered architecture, and responsible separation of concerns.

## ✅ Features

- Book rooms with date validation and balance checking
- Prevent double-booking for overlapping dates
- Handle user, room, and booking logic independently
- Clear exception handling with custom exception types
- Enumeration for `RoomType`
- Nicely formatted output for users and bookings
- Everything is done using Lists, no database.

---

## 🧱 Architecture

The system is composed of 3 core services, each implemented cleanly and independently:

### `UserService`
- Responsible for managing user registration and lookup
- Checks for user existence
- Return user by id
- Print all users

### `RoomService`
- Manages room registration and lookup
- Check for room existence

### `BookingService`
- Coordinates the booking process
- Checks availability, dates, and sufficient balance
- Prevents overlapping bookings
- Deducts booking cost from user upon success

---

## 📦 Core Components

### `RoomType` (Enum)
Defines room categories:
```java
public enum RoomType {
    STANDARD, JUNIOR, MASTER
}
