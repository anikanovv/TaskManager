package ru.anikanov.tm.service;

import ru.anikanov.tm.Enum.Role;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.repository.UserRepository;

import javax.jws.soap.SOAPBinding;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User persist(String login, String password, Role role) throws UnsupportedEncodingException, NoSuchAlgorithmException {
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

    public boolean auth(String login, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (login.isEmpty() || (login == null)) return false;
        if (password.isEmpty() || (password == null)) return false;
        User user = findOne(login);
        if (user == null) return false;
        return userRepository.auth(login, password);

    }

    public boolean endSession() {
        return userRepository.endSession();
    }

    public boolean updatePassword(String login, String oldOne, String newOne) {
        if (login.isEmpty() || (login == null)) return false;
        if (newOne.isEmpty() || (newOne == null)) return false;
        return userRepository.updatePassword(login, oldOne, newOne);
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
