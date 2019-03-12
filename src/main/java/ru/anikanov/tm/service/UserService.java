package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.repository.UserRepository;

import java.util.List;

public class UserService implements UserServiceInterface {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User persist(String login, String password, Role role) {
        if (login.isEmpty() || (login == null)) return null;
        if (userRepository.findOne(login) == null) {
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

    public boolean logIn(String login, String password) {
        if (login.isEmpty() || (login == null)) return false;
        if (password.isEmpty() || (password == null)) return false;
        User user = findOne(login, login);
        if (user == null) return false;
        return userRepository.logIn(login, password);

    }

    public boolean logOut() {
        return userRepository.logOut();
    }

    public boolean updatePassword(String login, String oldOne, String newOne) {
        if (login.isEmpty() || (login == null)) return false;
        if (newOne.isEmpty() || (newOne == null)) return false;
        return userRepository.updatePassword(login, oldOne, newOne);
    }

    public User findOne(String login, String userId) {
        if (login.isEmpty() || (login == null)) return null;
        return userRepository.findOne(login);
    }

    public List<User> findAll(String userId) {
        return userRepository.findAll();
    }

    public void remove(String login, String userId) {
        if (login.isEmpty() || (login == null)) return;
        userRepository.remove(login);
    }

    public void removeAll(String userId) {
        userRepository.removeAll();
    }
}
