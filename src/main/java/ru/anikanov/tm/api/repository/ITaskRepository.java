package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository {
    Task persist(Task task);

    void merge(Task task) throws Exception;

    void remove(String name);

    void removeAll();

    Task findOne(String name);

    List<Task> findAll();

    void removeWholeProject(String projectId);
}
