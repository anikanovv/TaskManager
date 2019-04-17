package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.repository.query.Param;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface ITaskRepository {

    @Nullable
    Task persist(@NotNull final Task task);

    void merge(@NotNull final Task task);

    void remove(@NotNull final Task task);

    void removeAll(@NotNull final String userId);

    void removeWholeProject(@NotNull final String projectId, @NotNull final String userId);

    Task findOne(@Param("id") @NotNull final String id, @Param("userId") @NotNull final String userId);

    @Nullable
    List findAll(@Param("id") @NotNull final String userId);

    @Nullable
    Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId);

    @Nullable
    Task findByPartOfDescription(@Param("partOfDescription") @NotNull final String partOfDescription, @Param("userId") @NotNull final String userId);


}
