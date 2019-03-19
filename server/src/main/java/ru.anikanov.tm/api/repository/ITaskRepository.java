package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository {

    @NotNull
    Task persist(@NotNull final Task task);

    void merge(@NotNull Task task) throws Exception;

    void remove(@NotNull final String name);

    void removeAll();

    @Nullable
    Task findOne(@NotNull final String name);

    @Nullable
    List<Task> findAll();

    void removeWholeProject(@NotNull final String projectId);

    @Nullable
    List<Task> sortedByStartDate();

    @Nullable
    List<Task> sortedByFinishDate();

    @Nullable
    List<Task> sortedByStatus();

    @Nullable
    Task findByPartOfName(@NotNull String partOfName);

    @Nullable
    Task findByPartOfDescription(@NotNull String partOfDescription);


}
