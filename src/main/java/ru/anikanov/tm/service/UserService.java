package ru.anikanov.tm.service;

import ru.anikanov.tm.command.project.Enum.Role;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.repository.UserRepository;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User persist(String login, String password, Role role) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (login.isEmpty() || (login == null)) return null;
        if (userRepository.findOne(login) != null) {
            if (password.isEmpty() || (password == null)) return null;
            if (role == null) return null;
            return userRepository.persist(login, password, role);
        }
        return null;
    }

    public void merge(String login, String password, Role role) {
        if (login.isEmpty() || (login == null)) return;
        if (password.isEmpty() || (password == null)) return;
        if (role == null) return;
        userRepository.merge(login, password, role);
    }

/*    public boolean auth(String login, String password) {
        boolean isAuth;
        User user = userMap.get(login);
        if ((user.getHashPassword() == password.hashCode())) {
            isAuth = true;
        } else {
            isAuth = false;
        }
        user.setAuth(true);
        return isAuth;
    }*/
/*
    public boolean endSession(String login, String password) {
        User user = userMap.get(login);
        user.setAuth(false);
        return user.isAuth();
    }*/

    public void updatePassword(String login, String oldOne, String newOne) {
        if (login.isEmpty() || (login == null)) return;
        if (newOne.isEmpty() || (newOne == null)) return;
        userRepository.updatePassword(login, oldOne, newOne);
    }

    public User findOne(String login) {
        if (login.isEmpty() || (login == null)) return null;
        return userRepository.findOne(login);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User remove(String login) {
        if (login.isEmpty() || (login == null)) return null;
        return userRepository.remove(login);
    }

    public void removeAll() {
        userRepository.removeAll();
    }
}
