package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Project;

import java.text.ParseException;
import java.util.List;

public interface IProjectRepository {
    AbstractEntity persist(AbstractEntity p);

    void merge(AbstractEntity p) throws ParseException;

    void remove(String name);

    void removeAll();

    AbstractEntity findOne(String name);

    List<AbstractEntity> findAll();

}
