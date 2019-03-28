package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository extends IRepository {

    @Nullable
    Task persist(@NotNull final Task task);

    Task merge(@NotNull final String taskId, @NotNull final String taskName, @NotNull final String description,
               @NotNull final String dateStart, @NotNull final String dateFinish, @NotNull final String userId);

    void remove(@NotNull final String name, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    @Nullable
    Task findOne(@NotNull final String name, @NotNull final String userId);

    @Nullable
    List<Task> findAll(@NotNull final String userId);

    void removeWholeProject(@NotNull final String projectId, @NotNull final String userId);

    @Nullable
    List<Task> sortedByStartDate(@NotNull final String userId);

    @Nullable
    List<Task> sortedByFinishDate(@NotNull final String userId);

    @Nullable
    List<Task> sortedByStatus(@NotNull final String userId);

    @Nullable
    Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId);

    @Nullable
    Task findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId);


}
