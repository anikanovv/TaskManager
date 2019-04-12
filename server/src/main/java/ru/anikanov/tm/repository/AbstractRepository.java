package ru.anikanov.tm.repository;

import java.util.List;

public abstract class AbstractRepository<T> {

//    abstract public void remove(String name) throws Exception;

    abstract public void removeAll();

    abstract public T findOne(String name);

    abstract public List<T> findAll();

}
