package ru.anikanov.tm.entity;

import ru.anikanov.tm.enumeration.Role;

import java.util.UUID;

public class User {
    private String id = UUID.randomUUID().toString();
    private String login;
    private String hashPassword;
    private Role role;

    public User(String login, String password, Role role) {
        this.login = login;
        this.hashPassword = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getRole() {
        return role.displayName();
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashPassword(String newpassword) {
        this.hashPassword = newpassword;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
