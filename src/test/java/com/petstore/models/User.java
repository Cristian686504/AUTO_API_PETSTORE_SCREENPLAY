package com.petstore.models;

public class User {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    private User() {}

    public static Builder builder() {
        return new Builder();
    }

    public long getId() { return id; }
    public String getUsername() { return username; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getPhone() { return phone; }
    public int getUserStatus() { return userStatus; }

    public static class Builder {
        private final User user = new User();

        public Builder id(long id) { user.id = id; return this; }
        public Builder username(String username) { user.username = username; return this; }
        public Builder firstName(String firstName) { user.firstName = firstName; return this; }
        public Builder lastName(String lastName) { user.lastName = lastName; return this; }
        public Builder email(String email) { user.email = email; return this; }
        public Builder password(String password) { user.password = password; return this; }
        public Builder phone(String phone) { user.phone = phone; return this; }
        public Builder userStatus(int userStatus) { user.userStatus = userStatus; return this; }

        public User build() { return user; }
    }
}
