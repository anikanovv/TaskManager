package ru.anikanov.tm.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@Transactional
@ApplicationScoped
public class TaskService extends AbstractService implements ITaskService {
    @Inject
    private EntityManager entityManager;
    @Inject
    private ITaskRepository taskRepository;

    @Nullable
    public Task persist(@Nullable final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        if ((userId == null) || userId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
//        try {
            if (projectId == null || projectId.isEmpty()) return null;
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            taskRepository.persist(newtask);
            return newtask;
//        } catch (Exception e) {
//            return null;
//        }

    }

    public void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
//        try {
            @Nullable Task task = taskRepository.findOne(taskId, userId);
            if (task == null) task = new Task();
            task.setName(taskName);
            task.setDescription(description);
            task.setStart(dateStart);
            task.setEnd(dateFinish);
            taskRepository.merge(task);
//        } catch (Exception e) {
//        }
    }

    public void remove(@Nullable final String taskId, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
//        try {
            @Nullable final Task task = taskRepository.findOne(taskId, userId);
            if (task == null) return;
            taskRepository.remove(task);
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//        }
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
//        try {
            taskRepository.removeAll(userId);
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//        }
    }

    @Nullable
    public Task findOne(@Nullable final String taskId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((taskId == null) || taskId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findOne(taskId, userId);
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findByPartOfName(partOfName, userId);
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findByPartOfDescription(partOfDescription, userId);
    }

    public List<Task> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        return taskRepository.findAll(userId);
    }

    @Nullable
    public List<Task> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
//        final ITaskRepository taskRepository = new TaskRepository(entityManager);
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
