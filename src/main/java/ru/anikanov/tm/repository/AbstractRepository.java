package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Project;

import java.text.ParseException;
import java.util.List;

public abstract class AbstractRepository<T extends AbstractEntity> {
    abstract public T persist(T t);

    abstract public void merge(T t) throws ParseException;

    abstract public void remove(String name);

    abstract public void removeAll();

    abstract public T findOne(String name);

    abstract public List<T> findAll();

}
