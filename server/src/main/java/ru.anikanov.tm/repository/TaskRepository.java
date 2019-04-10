package ru.anikanov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.Task;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@Getter
@ApplicationScoped
@NoArgsConstructor
public class TaskRepository implements ITaskRepository {
    @NotNull
    private EntityManager entityManager;

    @Inject
    public TaskRepository(EntityManager entityManager) {
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
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public void removeWholeProject(@NotNull final String projectId, @NotNull final String userId) {
        entityManager.createQuery("DELETE FROM Task task WHERE task.userId = :userId AND task.projectId = :projectId")
                .setParameter("userId", userId)
                .setParameter("projectId", projectId)
                .executeUpdate();
    }

    @Nullable
    public Task findOne(@NotNull final String taskId, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.id = :userId AND task.userId = :userId")
                .setParameter("userId", taskId)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Nullable
    public List<Task> findAll(@NotNull final String userId) {
        return entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Nullable
    public Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = :userId AND task.taskName LIKE :partOfName")
                .setParameter("userId", userId)
                .setParameter("partOfName", "%" + partOfName + "%")
                .getSingleResult();
    }

    @Nullable
    public Task findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        return (Task) entityManager.createQuery("SELECT task FROM Task task WHERE task.userId = :userId AND task.taskDescription LIKE :partOfDescription")
                .setParameter("userId", userId)
                .setParameter("partOfDescription", "%" + partOfDescription + "%")
                .getSingleResult();
    }
}
