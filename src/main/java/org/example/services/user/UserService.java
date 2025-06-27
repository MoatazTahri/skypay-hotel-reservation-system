package org.example.services.user;

import org.example.entities.User;

public interface UserService {
    void setUser(User user);
    boolean isUserExists(User user);
    User getUserById(int id);
    void printAllUsers();
}
