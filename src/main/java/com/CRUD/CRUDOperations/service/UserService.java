package com.CRUD.CRUDOperations.service;

import com.CRUD.CRUDOperations.model.User;
import com.CRUD.CRUDOperations.dto.UserSummaryDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private List<User> users = new CopyOnWriteArrayList<>();
    private AtomicLong nextId = new AtomicLong(1);

    /**
     * Creates a new user, assigns a unique ID, and adds it to the user list.
     * @param user The user to be created.
     * @return The created user with an assigned ID.
     */
    @PreAuthorize("hasRole('ADMIN')")
    public User createUser(User user) {
        user.setId(nextId.incrementAndGet());
        users.add(user);
        return user;
    }

    /**
     * Retrieves all users in the system.
     * @return A list of all users with sensitive data excluded.
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public List<UserSummaryDTO> getAllUsers() {
        return users.stream()
                .map(user -> new UserSummaryDTO(user.getId(), user.getName()))
                .toList();
    }

    /**
     * Retrieves a user by their unique ID.
     * @param id The ID of the user to retrieve.
     * @return An Optional containing the user if found, or empty if not found.
     */
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    /**
     * Updates an existing user's details.
     * @param id The ID of the user to update.
     * @param updatedUser The user object containing updated details.
     * @return An Optional containing the updated user if found, or empty if not found.
     */
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> userOptional = getUserById(id);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            return Optional.of(existingUser);
        }
        return Optional.empty();
    }

    /**
     * Deletes a user by their unique ID.
     * @param id The ID of the user to delete.
     * @return True if the user was successfully deleted, false otherwise.
     */
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}

package com.CRUD.CRUDOperations.dto;

public class UserSummaryDTO {
    private Long id;
    private String name;

    public UserSummaryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}