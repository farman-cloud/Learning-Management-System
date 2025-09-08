package com.farman.lmsApp;

public class Users {
    private String name;
    private String email;
    private String password;
    private Role role;

    public enum Role {
        LEARNER,
        INSTRUCTOR
    }

    public Users( String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
