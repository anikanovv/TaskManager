package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.User;

import java.util.List;

public interface IRepository<T> {

//    T persist(T t);

//    void merge(T t) throws Exception;

    void remove(String name) throws Exception;

    void removeAll();

    T findOne(String name);

    List<T> findAll();

//    void merge(T p);
}
