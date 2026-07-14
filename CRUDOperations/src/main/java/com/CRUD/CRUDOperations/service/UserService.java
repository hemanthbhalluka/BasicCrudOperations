package com.CRUD.CRUDOperations.service;

import com.CRUD.CRUDOperations.model.User;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private List<User> users = new ArrayList<>();
    private AtomicLong nextId = new AtomicLong(1);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public User createUser(@Valid User user) {
        user.setId(nextId.getAndIncrement());
        users.add(user);
        logger.info("User created: ID={}, Name={}, Email={}", user.getId(), user.getName(), user.getEmail());
        return user;
    }

    @Secured("ROLE_ADMIN")
    public List<User> getAllUsers() {
        logger.info("Fetching all users. Total count: {}", users.size());
        return new ArrayList<>(users);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Optional<User> getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID: ID must be a positive number and not null.");
        }
        Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
        if (user.isPresent()) {
            logger.info("User fetched by ID: ID={}, Name={}, Email={}", user.get().getId(), user.get().getName(), user.get().getEmail());
        } else {
            logger.warn("User not found for ID: {}", id);
        }
        return user;
    }

    @Secured("ROLE_ADMIN")
    public Optional<User> updateUser(Long id, @Valid User updatedUser) {
        Optional<User> existingUser = getUserById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            logger.info("User updated: ID={}, New Name={}, New Email={}", user.getId(), user.getName(), user.getEmail());
            return Optional.of(user);
        } else {
            logger.warn("Update failed. User not found for ID: {}", id);
        }
        return Optional.empty();
    }

    @Secured("ROLE_ADMIN")
    public boolean deleteUser(Long id) {
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (removed) {
            logger.info("User deleted: ID={}", id);
        } else {
            logger.warn("Delete failed. User not found for ID: {}", id);
        }
        return removed;
    }
}