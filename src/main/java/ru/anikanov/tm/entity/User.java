package ru.anikanov.tm.entity;

import ru.anikanov.tm.enumeration.Role;

import java.util.UUID;

public class User extends AbstractEntity {
    private String name;
    private String hashPassword;
    private Role role;

    public User(String login, String password, Role role) {
        this.name = login;
        this.hashPassword = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return name;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public String getRoleName() {
        return role.displayName();
    }

    public Role getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.name = login;
    }

    public void setHashPassword(String newpassword) {
        this.hashPassword = newpassword;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
