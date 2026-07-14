package com.CRUD.CRUDOperations.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class User {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    public User() {}

    public User(Long id, String name, String email) {
        this.id = id;
        setName(name);
        setEmail(email);
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty() || name.length() > 50) {
            throw new IllegalArgumentException("Name must be between 1 and 50 characters and cannot be null");
        }
        PolicyFactory policy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);
        this.name = policy.sanitize(name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Email should be valid and cannot be null");
        }
        this.email = email;
    }
}