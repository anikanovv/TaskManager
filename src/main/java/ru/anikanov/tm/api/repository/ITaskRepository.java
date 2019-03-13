package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Task;

import java.text.ParseException;
import java.util.List;

public interface ITaskRepository {
    AbstractEntity persist(AbstractEntity task);

    void merge(AbstractEntity task) throws ParseException;

    void remove(String name);

    void removeAll();

    AbstractEntity findOne(String name);

    List<AbstractEntity> findAll();

    void removeWholeProject(String projectId);
}
