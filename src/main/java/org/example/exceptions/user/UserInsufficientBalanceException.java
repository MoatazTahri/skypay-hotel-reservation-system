package org.example.exceptions.user;

public class UserInsufficientBalanceException extends RuntimeException {
    public UserInsufficientBalanceException(String message) {
        super(message);
    }
}
