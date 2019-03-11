package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> userMap = new LinkedHashMap<>();

    public User persist(String login, String password, Role role) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = new User(login, getHash(password), role);
        return userMap.put(login, user);
    }

    public void merge(String login, String password, Role role) {
        User user = userMap.get(login);
        user.setHashPassword(password);
        user.setRole(role);
    }

    public boolean auth(String login, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = findOne(login);
        if (user.getHashPassword().equals(getHash(password))) {
            return true;
        } else return false;
    }

    public boolean endSession() {
        return true;
    }

    public boolean updatePassword(String login, String oldOne, String newOne) {
        User user = userMap.get(login);
        user.setHashPassword(newOne);
        return true;
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

    public String getHash(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
  /*      MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(password.getBytes("utf-8"));
        String string = md.digest().toString();*/
        String string = password;
        return string;
    }
}
