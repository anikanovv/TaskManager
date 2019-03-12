package ru.anikanov.tm.service;


import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.List;

public interface UserServiceInterface {

    User persist(String login, String password, Role role);

    void merge(String login, String password, Role role);

    void remove(String login, String userId);

    void removeAll(String userId);

    User findOne(String login, String userId);

    List<User> findAll(String userId);

    boolean logIn(String login, String password);

    boolean updatePassword(String login, String oldOne, String newOne);

}
