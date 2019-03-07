package ru.anikanov.tm.entity;

import ru.anikanov.tm.command.project.Enum.Role;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class User {
    private String id = UUID.randomUUID().toString();
    private String login;
    private String hashPassword;
    private Role role;
    private boolean isAuth;

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

    public boolean isAuth() {
        return isAuth;
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

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

}
