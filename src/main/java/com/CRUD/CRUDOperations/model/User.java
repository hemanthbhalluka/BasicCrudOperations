package com.CRUD.CRUDOperations.model;

// ────────────────────────────────────────────────────────────

// [REFACTOR] STANDARD_VIOLATION (MEDIUM): The package declaration and class definition are not separated by a blank line, which violates stand

package com.CRUD.CRUDOperations.model;





// ────────────────────────────────────────────────────────────
// [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a class-level Javadoc comment to describe the purpose of the User class.
/**
 * Represents a user entity with an ID, name, and email.
 */
public class User {
// [SUGGESTION END]
// ────────────────────────────────────────────────────────────
public class User {
// [REFACTOR END]
// ────────────────────────────────────────────────────────────

    private Long id;
    private String name;
    private String email;

    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the default constructor to explain its purpose.
    /**
     * Default constructor for creating an empty User instance.
     */
    public User() {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        public User() {
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
    // [REFACTOR] STANDARD_VIOLATION (MEDIUM): The code is written in a single line, which violates readability and maintainability standards.
        public User() {
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the parameterized constructor to explain its purpose and parameters.
    /**
     * Constructs a User instance with the specified ID, name, and email.
     *
     * @param id    the unique identifier for the user
     * @param name  the name of the user
     * @param email the email address of the user
     */
    public User(Long id, String name, String email) {
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
        public User(Long id, String name, String email) {
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the getId method to explain its purpose.
    /**
     * Gets the unique identifier of the user.
     *
     * @return the user ID
     */
    public Long getId() {
    return id;
    }
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
         */
        public Long getId() {
            return id;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the setId method to explain its purpose and parameter validation.
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
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
         */
        public void setId(Long id) {
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID must be non-null and positive");
            }
            this.id = id;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("ID must be non-null and positive");
            }
            this.id = id;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): The getters and setters lack Javadoc comments explaining their purpose and behavior.
        // ────────────────────────────────────────────────────────────
        // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the getName method to explain its purpose.
        /**
         * Gets the name of the user.
         *
         * @return the user's name
         */
        public String getName() {
        return name;
        }
        // [SUGGESTION END]
        // ────────────────────────────────────────────────────────────
         */
        public String getName() {
            return name;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the setName method to explain its purpose.
    /**
     * Sets the name of the user.
     *
     * @param name the user's name to set
     */
    public void setName(String name) {
    this.name = name;
    }
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
         */
        public void setName(String name) {
            this.name = name;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // [REFACTOR] DOCUMENTATION (LOW): The getters and setters lack Javadoc comments explaining their purpose and behavior.
        // ────────────────────────────────────────────────────────────
        // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the getEmail method to explain its purpose.
        /**
         * Gets the email address of the user.
         *
         * @return the user's email address
         */
        public String getEmail() {
        return email;
        }
        // [SUGGESTION END]
        // ────────────────────────────────────────────────────────────
         */
        public String getEmail() {
            return email;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────

    // ────────────────────────────────────────────────────────────
    // ────────────────────────────────────────────────────────────
    // [SUGGESTION] DOCSTRINGS_AND_COMMENTS: Added a Javadoc comment to the setEmail method to explain its purpose and validation logic.
    /**
     * Sets the email address of the user.
     * Validates the email format before setting.
     *
     * @param email the user's email address to set
     * @throws IllegalArgumentException if the email format is invalid
     */
    public void setEmail(String email) {
    if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
    throw new IllegalArgumentException("Invalid email format");
    }
    this.email = email;
    }
    // [SUGGESTION END]
    // ────────────────────────────────────────────────────────────
         */
        public void setEmail(String email) {
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            this.email = email;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
            if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Invalid email format");
            }
            this.email = email;
        }
    // [REFACTOR END]
    // ────────────────────────────────────────────────────────────
}

