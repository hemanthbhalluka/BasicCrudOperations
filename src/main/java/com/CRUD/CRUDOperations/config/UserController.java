package com.CRUD.CRUDOperations.config;

import com.CRUD.CRUDOperations.model.User;
import com.CRUD.CRUDOperations.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// ────────────────────────────────────────────────────────────
// ────────────────────────────────────────────────────────────
// [SUGGESTION] DEAD_CODE_REMOVAL: Removed wildcard import to improve clarity and avoid unnecessary imports.
import java.util.List;
import java.util.Optional;
// [SUGGESTION END]
// ────────────────────────────────────────────────────────────
import java.util.List;
import java.util.Optional;
// [REFACTOR END]
// ────────────────────────────────────────────────────────────

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): Missing method-level comments for public methods to explain their purpose and usage.
        /**
         * Creates a new user.
         *
         * @param user the user to be created
         * @return the created user wrapped in a ResponseEntity
         */
        @PostMapping
        public ResponseEntity<User> createUser(@RequestBody User user) {
            User createdUser = userService.createUser(user);
            return ResponseEntity.ok(createdUser);
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): Missing method-level comments for public methods to explain their purpose and usage.
        /**
         * Retrieves all users.
         *
         * @return a list of all users wrapped in a ResponseEntity
         */
        @GetMapping
        public ResponseEntity<List<User>> getAllUsers() {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): Missing method-level comments for public methods to explain their purpose and usage.
        /**
         * Retrieves a user by their ID.
         *
         * @param id the ID of the user to retrieve
         * @return the user wrapped in a ResponseEntity, or a 404 status if not found
         */
        @GetMapping("/{id}")
        public ResponseEntity<User> getUserById(@PathVariable Long id) {
            Optional<User> user = userService.getUserById(id);
            return user.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
    }

    // Update
    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): Missing method-level comments for public methods to explain their purpose and usage.
        /**
         * Updates an existing user.
         *
         * @param id the ID of the user to update
         * @param user the updated user details
         * @return the updated user wrapped in a ResponseEntity, or a 404 status if not found
         */
        @PutMapping("/{id}")
        public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
            Optional<User> updatedUser = userService.updateUser(id, user);
            return updatedUser.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
    }

    // Delete
    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): Missing method-level comments for public methods to explain their purpose and usage.
        /**
         * Deletes a user by their ID.
         *
         * @param id the ID of the user to delete
         * @return a 200 status if the user was deleted, or a 404 status if not found
         */
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
            boolean deleted = userService.deleteUser(id);
            return deleted ? ResponseEntity.ok().build()
                    : ResponseEntity.notFound().build();
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
    }
}

