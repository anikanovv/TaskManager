package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> userMap = new LinkedHashMap<>();

    public User persist(String login, String password, Role role) {
        User user = new User(login, password, role);
        return userMap.put(login, user);
    }

    public void merge(String login, String password, Role role) {
        User user = userMap.get(login);
        user.setHashPassword(password);
        user.setRole(role);
    }

    public boolean logIn(String login, String password) {
        User user = findOne(login);
        if (user.getHashPassword().equals(password)) {
            return true;
        } else return false;
    }

    public boolean logOut() {
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

}
