package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository {

    @NotNull
    Task persist(@NotNull Task task);

    void merge(@NotNull Task task) throws Exception;

    void remove(@NotNull String name);

    void removeAll();

    @Nullable
    Task findOne(@NotNull String name);

    @Nullable
    List<Task> findAll();

    void removeWholeProject(@NotNull String projectId);
}
