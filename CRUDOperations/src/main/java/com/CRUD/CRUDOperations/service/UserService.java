package com.CRUD.CRUDOperations.service;

import com.CRUD.CRUDOperations.model.User;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public User createUser(@Valid User user) {
        user.setId(nextId.getAndIncrement());
        users.add(user);
        return user;
    }

    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Optional<User> getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be a positive number and not null.");
        }
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    @Secured("ROLE_ADMIN")
    public Optional<User> updateUser(Long id, @Valid User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Secured("ROLE_ADMIN")
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}