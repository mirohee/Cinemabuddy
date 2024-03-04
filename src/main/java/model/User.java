package model;

public class User {

    private String firstname;
    private String lastname;
    private String email;
    private String age;
    private String password;

    public User(String firstname, String lastname, String email, String age, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.age = age;
        this.password = password;
    }
}
