package ru.anikanov.tm.api.repository;

import org.apache.ibatis.annotations.Param;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository {

    @Nullable
    Task persist(@NotNull final Task task);

    Task merge(@NotNull final String taskId, @NotNull final String taskName, @NotNull final String description,
               @NotNull final String dateStart, @NotNull final String dateFinish, @NotNull final String userId);

    void remove(@NotNull final String name, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    Task findOne(@Param("id") @NotNull final String id, @Param("userId") @NotNull final String userId);

    @Nullable
    List<Task> findAll(@Param("id") @NotNull final String userId);

    void removeWholeProject(@NotNull final String projectId, @NotNull final String userId);

    @Nullable

    Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId);

    @Nullable
    Task findByPartOfDescription(@Param("partOfDescription") @NotNull final String partOfDescription, @Param("userId") @NotNull final String userId);


}
