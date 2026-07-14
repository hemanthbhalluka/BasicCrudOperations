package com.CRUD.CRUDOperations.model;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Represents a user entity with an ID, name, and email.
 */
public class User {

    private Long id;
    private String name;
    private String email;

    /**
     * Default constructor for creating an empty User instance.
     */
    public User() {
    }

    /**
     * Constructs a User instance with the specified ID, name, and email.
     *
     * @param id    the unique identifier for the user
     * @param name  the name of the user
     * @param email the email address of the user
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public User(Long id, String name, String email) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be non-null and positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name must be non-null and not empty");
        }
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
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
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id the user ID to set
     * @throws IllegalArgumentException if the ID is null or non-positive
     */
    public void setId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID must be non-null and positive");
        }
        this.id = id;
    }

    /**
     * Gets the name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the user's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the email address of the user.
     * Access is restricted to authorized users only.
     *
     * @return the user's email address
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     * Validates the email format before setting.
     *
     * @param email the user's email address to set
     * @throws IllegalArgumentException if the email format is invalid
     */
    @PreAuthorize("hasRole('ADMIN')")
    public void setEmail(String email) {
        if (email == null || !EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }
}