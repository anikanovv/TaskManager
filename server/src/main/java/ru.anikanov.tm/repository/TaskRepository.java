package ru.anikanov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.Task;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
@Getter
public class TaskRepository implements ITaskRepository {

    @NotNull
    private EntityManager entityManager;

    public TaskRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Nullable
    public Task persist(@NotNull final Task entity) {
        entityManager.persist(entity);
        return entity;
    }

    public void merge(@NotNull final Task task) {
        entityManager.merge(task);
    }

    public void remove(@NotNull final Task task) {
        entityManager.remove(task);
    }

    public void removeAll(@NotNull final String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

    public void removeWholeProject(@NotNull final String projectId, @NotNull final String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId = ?1 AND task.projectId = ?2")
                .setParameter(1, userId)
                .setParameter(2, projectId)
                .executeUpdate();
    }

    @Nullable
    public Task findOne(@NotNull final String taskId, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.id = ?1 AND task.userId = ?2")
                .setParameter(1, taskId)
                .setParameter(2, userId)
                .getSingleResult();
    }

    @Nullable
    public List<Task> findAll(@NotNull final String userId) {
        return entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = ?1")
                .setParameter(1, userId)
                .getResultList();
    }

    @Nullable
    public Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = ?1 AND task.taskName LIKE ?2")
                .setParameter(1, userId)
                .setParameter(2, partOfName)
                .getSingleResult();
    }

    @Nullable
    public Task findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = ?1 AND task.taskDescription LIKE ?2")
                .setParameter(1, userId)
                .setParameter(2, partOfDescription)
                .getSingleResult();
    }
}
