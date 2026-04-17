package com.CRUD.CRUDOperations.service;

import com.CRUD.CRUDOperations.model.User;
import org.springframework.stereotype.Service;

// ────────────────────────────────────────────────────────────
// [REFACTOR] STANDARD_VIOLATION (HIGH): Wildcard imports are used in the package declaration, violating the rule: Wildcard ('on-demand') imp
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
// [REFACTOR END]
// ────────────────────────────────────────────────────────────

@Service
public class UserService {
    // ────────────────────────────────────────────────────────────
    // [REFACTOR] QUALITY (HIGH): The 'users' list is not thread-safe, which can lead to concurrency issues in a multi-threaded enviro
        private List<User> users = new CopyOnWriteArrayList<>();
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [REFACTOR] QUALITY (HIGH): The 'nextId' variable is not thread-safe, which can cause duplicate IDs in a multi-threaded environm
        private AtomicLong nextId = new AtomicLong(1);
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a docstring to describe the purpose of the 'createUser' method.
    /**
         * Creates a new user, assigns a unique ID, and adds it to the user list.
         * @param user The user to be created.
         * @return The created user with an assigned ID.
         */
        public User createUser(User user) {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        // ────────────────────────────────────────────────────────────
        // [SUGGESTION] CODE_SIMPLIFICATION: Simplified the increment operation for 'nextId' to use 'incrementAndGet' for thread safety and clari
        user.setId(nextId.incrementAndGet());
        // [SUGGESTION END]
        // ────────────────────────────────────────────────────────────
        users.add(user);
        return user;
    }

    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a docstring to describe the purpose of the 'getAllUsers' method.
    /**
         * Retrieves all users in the system.
         * @return A list of all users.
         */
        public List<User> getAllUsers() {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        return new ArrayList<>(users);
    }

    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a docstring to describe the purpose of the 'getUserById' method.
    /**
         * Retrieves a user by their unique ID.
         * @param id The ID of the user to retrieve.
         * @return An Optional containing the user if found, or empty if not found.
         */
        public Optional<User> getUserById(Long id) {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a docstring to describe the purpose of the 'updateUser' method.
    /**
         * Updates an existing user's details.
         * @param id The ID of the user to update.
         * @param updatedUser The user object containing updated details.
         * @return An Optional containing the updated user if found, or empty if not found.
         */
        public Optional<User> updateUser(Long id, User updatedUser) {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        // ────────────────────────────────────────────────────────────
        // [SUGGESTION] LOCAL_VARIABLE_RENAMING: Renamed 'existingUser' to 'userOptional' for better clarity and consistency with naming conventions.
        Optional<User> userOptional = getUserById(id);
        // [SUGGESTION END]
        // ────────────────────────────────────────────────────────────
        if (existingUser.isPresent()) {
            // ────────────────────────────────────────────────────────────
            // [SUGGESTION] LOCAL_VARIABLE_RENAMING: Renamed 'user' to 'existingUser' for better clarity and alignment with its purpose.
            User existingUser = userOptional.get();
            // [SUGGESTION END]
            // ────────────────────────────────────────────────────────────
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return Optional.of(user);
        }
        return Optional.empty();
    }

    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a docstring to describe the purpose of the 'deleteUser' method.
    /**
         * Deletes a user by their unique ID.
         * @param id The ID of the user to delete.
         * @return True if the user was successfully deleted, false otherwise.
         */
        public boolean deleteUser(Long id) {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        return users.removeIf(user -> user.getId().equals(id));
    }
}

