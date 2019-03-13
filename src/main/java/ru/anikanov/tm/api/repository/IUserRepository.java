package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.User;

import java.util.List;

public interface IUserRepository extends IRepository {

    User persist(User user);

    void merge(User user);

    void remove(String login);

    void removeAll();

    User findOne(String login);

    List<User> findAll();

    boolean logIn(String login, String password);

    boolean logOut();

    boolean updatePassword(String login, String oldOne, String newOne);


}
