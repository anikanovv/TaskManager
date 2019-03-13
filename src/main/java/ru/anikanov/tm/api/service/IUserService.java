package ru.anikanov.tm.api.service;


import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.List;

public interface IUserService {

    AbstractEntity persist(String login, String password, Role role);

    void merge(String login, String password, Role role);

    void remove(String login, String userId);

    void removeAll(String userId);

    AbstractEntity findOne(String login, String userId);

    List<AbstractEntity> findAll(String userId);

    boolean logIn(String login, String password);

    boolean logOut();

    boolean updatePassword(String login, String oldOne, String newOne);

}
