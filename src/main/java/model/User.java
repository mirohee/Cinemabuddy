package model;

import org.mindrot.jbcrypt.BCrypt;

public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String age;
    private String hashedPassword;

    public User(String firstname, String lastname, String email, String age, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.hashedPassword = hashPassword(password);
    }

    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();

        return BCrypt.hashpw(password, salt);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAge() {
        return age;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}

