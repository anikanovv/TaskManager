package ru.anikanov.tm.repository;

import ru.anikanov.tm.Role;
import ru.anikanov.tm.entity.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    Map<String, User> userMap = new LinkedHashMap<>();

    public User persist(String login, String password, Role role) {
        return userMap.put(login, new User(login, password, role));
    }

    public void merge(String login, String password, Role role) {
        User user = userMap.get(login);
        user.setHashPassword(password);
        user.setRole(role);
    }

    public boolean auth(String login, String password) {
        boolean isAuth;
        User user = userMap.get(login);
        if ((user.getHashPassword() == password.hashCode())) {
            isAuth = true;
        } else {
            isAuth = false;
        }
        user.setAuth(true);
        return isAuth;
    }

    public boolean endSession(String login, String password) {
        User user = userMap.get(login);
        user.setAuth(false);
        return user.isAuth();
    }
    
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
}
