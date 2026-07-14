package com.CRUD.CRUDOperations.model;

<<<<<<< HEAD
import org.apache.commons.validator.routines.EmailValidator;
=======
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Represents an immutable user entity with an ID, name, and email.
 */
<<<<<<< HEAD
public class User {
=======
public final class User {
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495

    private final Long id;
    private final String name;

<<<<<<< HEAD
    /**
     * Default constructor for creating an empty User instance.
     */
    public User() {
    }
=======
    @NotNull(message = "Email must not be null")
    @Email(message = "Invalid email format")
    private final String email;
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495

    /**
     * Constructs a User instance with the specified ID, name, and email.
     *
     * @param id    the unique identifier for the user
     * @param name  the name of the user
     * @param email the email address of the user
<<<<<<< HEAD
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
=======
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
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495
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
<<<<<<< HEAD
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
=======
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getName() {
        return name;
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495
    }

    /**
     * Gets the email address of the user.
<<<<<<< HEAD
     * Access is restricted to authorized users only.
=======
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495
     *
     * @return the user's email address
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public String getEmail() {
        return email;
    }
<<<<<<< HEAD

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
=======
>>>>>>> 9378230808a04e74c9da489695cbb5cc5d92d495
}