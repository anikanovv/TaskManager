package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.User;

import java.util.List;

public interface UserRepositoryInterface {

    User persist();

    void merge();

    void remove();

    void removeall();

    User findone();

    List<User> findall();

    boolean logIn();

    boolean logOut();

    boolean updatePassword();


}
