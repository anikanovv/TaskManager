package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.entity.Task;

import java.util.Comparator;
import java.util.List;

public class TaskService extends AbstractService implements ITaskService {
    @NotNull
    private ITaskRepository taskRepository;

    public TaskService(@NotNull final IProjectRepository pr, @NotNull final ITaskRepository tr, @NotNull final IUserRepository ur) {
        taskRepository = tr;
    }

    @Nullable
    public Task persist(@Nullable final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final Task task = taskRepository.findOne(taskName, userId);
        if (task != null) return null;
        if (projectId == null || projectId.isEmpty()) return null;
        if ((description == null) || description.isEmpty()) return null;
        if ((dateStart == null) || dateStart.isEmpty()) return null;
        if ((dateFinish == null) || dateFinish.isEmpty()) return null;
        try {
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            return taskRepository.persist(newtask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public Task findOne(@Nullable final String taskId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((taskId == null) || taskId.isEmpty()) return null;
        @Nullable final Task task = taskRepository.findOne(taskId, userId);
        if (task == null) return null;
        if (userId.equals(task.getUserId())) return task;
        else return null;
    }

    public List<Task> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        return taskRepository.findAll(userId);
    }

    public void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        try {
            taskRepository.merge(taskId, taskName, description, dateStart, dateFinish, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@Nullable final String taskId, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        taskRepository.remove(taskId, userId);
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        taskRepository.removeAll(userId);
    }

    @Nullable
    public List<Task> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Task> tasks = taskRepository.findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return taskRepository.findByPartOfName(partOfName, userId);
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        return taskRepository.findByPartOfDescription(partOfDescription, userId);
    }
}
