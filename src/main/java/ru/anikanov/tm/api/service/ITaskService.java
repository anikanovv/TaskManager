package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskService {
    @Nullable
    Task persist(String projectId, String taskName, String description, String dateStart, String dateFinish, String userId);

    void merge(String taskId, String taskName, String description, String dateStart, String dateFinish, String userId);

    void remove(String taskId, String userId);

    void removeAll(String userId);

    @Nullable
    Task findOne(String name, String userId);

    @Nullable
    List<Task> findAll(String userId);
}
