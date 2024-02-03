package ru.gb.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository {
    private final List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
    }

    public User add(User user) {
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getById(UUID id) {
        return users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }

    public User getByName(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findFirst().orElse(null);
    }

    public User getByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public User update(User user) {
        User userToUpdate = getById(user.getId());
        if (userToUpdate != null) {
            userToUpdate.setName(user.getName());
            userToUpdate.setEmail(user.getEmail());
            userToUpdate.setAge(user.getAge());
            return userToUpdate;
        }
        return null;
    }

    public boolean delete(UUID id) {
        return users.removeIf(u -> u.getId().equals(id));
    }

}
