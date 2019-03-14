package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskService {
    @Nullable
    Task persist(@Nullable final String projectId, @Nullable final String taskName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId);

    void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId);

    void remove(@Nullable final String taskId, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    @Nullable
    Task findOne(@Nullable final String name, @NotNull final String userId);

    @Nullable
    List<Task> findAll(@NotNull final String userId);
}
