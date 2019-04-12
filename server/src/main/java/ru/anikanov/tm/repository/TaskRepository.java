package ru.anikanov.tm.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends EntityRepository<Task, String> {
    void persist(@NotNull final Task entity);

    void merge(@NotNull final Task task);

    void remove(@NotNull final Task task);

    @Query("DELETE FROM Task task WHERE task.userId = :userId")
    void removeAll(@QueryParam("userId") @NotNull final String userId);

    @Query("DELETE FROM Task task WHERE task.userId = :userId AND task.projectId = :projectId")
    void removeWholeProject(@QueryParam("projectId") @NotNull final String projectId, @QueryParam("userId") @NotNull final String userId);

    @Query("SELECT task FROM Task task WHERE task.id = :taskId AND task.userId = :userId")
    Task findOne(@QueryParam("taskId") @NotNull final String taskId, @QueryParam("userId") @NotNull final String userId);

    @Query("SELECT task FROM Task task WHERE task.userId = :userId")
    List<Task> findAll(@QueryParam("userId") @NotNull final String userId);

    @Query("SELECT task FROM Task task WHERE task.userId = :userId AND task.taskName LIKE :partOfName")
    Task findByPartOfName(@QueryParam("partOfName") @NotNull final String partOfName, @QueryParam("userId") @NotNull final String userId);

    @Query("SELECT task FROM Task task WHERE task.userId = :userId AND task.taskDescription LIKE :partOfDescription")
    Task findByPartOfDescription(@QueryParam("partOfDescription") @NotNull final String partOfDescription, @QueryParam("userId") @NotNull final String userId);
}
