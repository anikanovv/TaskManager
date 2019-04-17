package ru.anikanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    Task save(@NotNull final Task entity);

    void delete(@NotNull final Task task);

    void deleteAllByUserId(@Param("userId") @NotNull final String userId);

    Task findByUserIdAndId(@Param("userId") @NotNull final String userId, @Param("id") @NotNull final String taskId);

    List<Task> findAllByUserId(@Param("userId") @NotNull final String userId);

    Task findByUserIdAndName(@NotNull final String userId, @NotNull final String name);

    Task findByUserIdAndDescription(@Param("userId") @NotNull final String userId, @Param("description") @NotNull final String description);
}
