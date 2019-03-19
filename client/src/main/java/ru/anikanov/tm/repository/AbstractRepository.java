package ru.anikanov.tm.repository;

import ru.anikanov.tm.api.repository.IRepository;

import java.util.List;

public abstract class AbstractRepository<T> implements IRepository {

    abstract public void remove(String name) throws Exception;

    abstract public void removeAll();

    abstract public T findOne(String name);

    abstract public List<T> findAll();

}
