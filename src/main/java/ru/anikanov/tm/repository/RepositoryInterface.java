package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.User;

import java.util.List;

public interface RepositoryInterface<T> {

    T persist(T t, String userId);

    void merge(T t, String userId);

    void remove(String name, String userId);

    void removeAll(String userId);

    T findOne(String name, String userId);

    List<T> findAll(String userId);
}
