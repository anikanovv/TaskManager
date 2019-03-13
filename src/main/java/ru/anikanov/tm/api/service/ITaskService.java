package ru.anikanov.tm.api.service;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskService {
    AbstractEntity persist(String projectId, String taskName, String description, String dateStart, String dateFinish, String userId);

    void merge(String taskId, String taskName, String description, String dateStart, String dateFinish, String userId);

    void remove(String taskId, String userId);

    void removeAll(String userId);

    AbstractEntity findOne(String name, String userId);

    List<AbstractEntity> findAll(String userId);
}
