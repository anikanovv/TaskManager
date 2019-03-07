package ru.anikanov.tm.repository;

import ru.anikanov.tm.command.project.Enum.Role;
import ru.anikanov.tm.entity.User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> userMap = new LinkedHashMap<>();

    public User persist(String login, String password, Role role) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return userMap.put(login, new User(login, getHash(password), role));
    }

    public void merge(String login, String password, Role role) {
        User user = userMap.get(login);
        user.setHashPassword(password);
        user.setRole(role);
    }

    public boolean auth(String login, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userMap.get(login);
        if (user.getHashPassword().equals(getHash(password))) {
            return true;
        } else return false;
    }

   /* public boolean endSession(String login, String password) {
        User user = userMap.get(login);
        user.setAuth(false);
        return user.isAuth();
    }*/

    public void updatePassword(String login, String oldOne, String newOne) {
        User user = userMap.get(login);
        user.setHashPassword(newOne);
    }

    public User findOne(String login) {
        return userMap.get(login);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userMap.forEach((k, v) -> users.add(v));
        return users;
    }

    public User remove(String login) {
        return userMap.remove(login);
    }

    public void removeAll() {
        userMap.clear();
    }

    private String getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(password.getBytes("utf-8"));
        String string = md.digest().toString();
        return string;
    }
}
