package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    AbstractEntity persist(AbstractEntity user);

    void merge(AbstractEntity user);

    void remove(String login);

    void removeAll();

    AbstractEntity findOne(String login);

    List<AbstractEntity> findAll();

    boolean logIn(String login, String password);

    boolean logOut();

    boolean updatePassword(String login, String oldOne, String newOne);


}
