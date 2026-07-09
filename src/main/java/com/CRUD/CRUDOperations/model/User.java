package com.CRUD.CRUDOperations.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Represents an immutable user entity with an ID, name, and email.
 */
public final class User {

    private final Long id;
    private final String name;

    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email format")
    private final String email;

    /**
     * Constructs a User instance with the specified ID, name, and email.
     *
     * @param id    the unique identifier for the user
     * @param name  the name of the user
     * @param email the email address of the user
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public User(Long id, String name, String email) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must not be null and must be positive");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name must not be null or empty");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the unique identifier of the user.
     *
     * @return the user ID
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Long getId() {
        return id;
    }

    /**
     * Gets the name of the user.
     *
     * @return the user's name
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getName() {
        return name;
    }

    /**
     * Gets the email address of the user.
     *
     * @return the user's email address
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getEmail() {
        return email;
    }
}