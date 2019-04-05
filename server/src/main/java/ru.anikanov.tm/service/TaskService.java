package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.TaskRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Comparator;
import java.util.List;

public class TaskService extends AbstractService implements ITaskService {
    private final EntityManagerFactory factory;

    @Inject
    public TaskService(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Nullable
    public Task persist(@Nullable final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            if (projectId == null || projectId.isEmpty()) return null;
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            entityManager.getTransaction().begin();
            taskRepository.persist(newtask);
            entityManager.getTransaction().commit();
            return newtask;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }

    }

    public void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            @Nullable Task task = taskRepository.findOne(taskId, userId);
            if (task == null) task = new Task();
            task.setName(taskName);
            task.setDescription(description);
            task.setStart(dateStart);
            task.setEnd(dateFinish);
            entityManager.getTransaction().begin();
            taskRepository.merge(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(@Nullable final String taskId, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            @Nullable final Task task = taskRepository.findOne(taskId, userId);
            if (task == null) return;
            entityManager.getTransaction().begin();
            taskRepository.remove(task);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
            entityManager.getTransaction().begin();
            taskRepository.removeAll(userId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public Task findOne(@Nullable final String taskId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((taskId == null) || taskId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findOne(taskId, userId);
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findByPartOfName(partOfName, userId);
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findByPartOfDescription(partOfDescription, userId);
    }

    public List<Task> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findAll(userId);
    }

    @Nullable
    public List<Task> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final TaskRepository taskRepository = new TaskRepository(entityManager);
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
