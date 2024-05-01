package model;

/**
 * Represents a user entity with basic information.
 */
public class User {
    private String firstname;
    private String lastname;
    private String email;
    private String age;
    private String password;

    /**
     * Constructs a new User object with the given information.
     *
     * @param firstname The first name of the user.
     * @param lastname  The last name of the user.
     * @param email     The email of the user.
     * @param age       The age of the user.
     * @param password  The password of the user.
     */
    public User(String firstname, String lastname, String email, String age, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.password = password;
    }

    /**
     * Gets the first name of the user.
     *
     * @return The first name of the user.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Gets the last name of the user.
     *
     * @return The last name of the user.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the age of the user.
     *
     * @return The age of the user.
     */
    public String getAge() {
        return age;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }
}
