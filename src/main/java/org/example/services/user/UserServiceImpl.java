package org.example.services.user;

import org.example.entities.User;
import org.example.exceptions.user.UserAlreadyExistsException;
import org.example.exceptions.user.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class UserServiceImpl implements UserService {
    private final List<User> users = new ArrayList<>();

    @Override
    public void setUser(User user) throws UserAlreadyExistsException {
        if (users.stream().anyMatch(u -> u.getId() == user.getId())) {
            throw new UserAlreadyExistsException("User with id " + user.getId() + " already exists");
        }
        users.add(user);
    }

    @Override
    public boolean isUserExists(User user) {
        return users.stream().anyMatch(u -> u.getId() == user.getId());
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException {
        return users.stream()
                .filter(u -> u.getId() == id).findFirst()
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public void printAllUsers() {
        ListIterator<User> usersIterator = users.listIterator(users.size());
        // Printing users from latest to oldest
        while (usersIterator.hasPrevious()) {
            User user = usersIterator.previous();
            System.out.println("User ID: " + user.getId() + " Balance: " + user.getBalance());
        }
    }
}
