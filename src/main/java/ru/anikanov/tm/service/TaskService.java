package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.entity.User;

import java.util.List;

public class TaskService extends AbstractService implements ITaskService {
    @NotNull
    private IProjectRepository projectRepository;
    @NotNull
    private ITaskRepository taskRepository;
    @NotNull
    private IUserRepository userRepository;

    public TaskService(@NotNull IProjectRepository pr, @NotNull ITaskRepository tr, @NotNull IUserRepository ur) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
    }

    @Nullable
    public Task findOne(@Nullable final String taskId, @NotNull final String userId) {
        if (taskId.isEmpty() || (taskId == null)) return null;
        return taskRepository.findOne(taskId);
    }

    @Nullable
    public Task persist(@NotNull final String projectId, @Nullable final String taskName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if (taskName.isEmpty() || (taskName == null)) return null;
        @Nullable final Task task = taskRepository.findOne(taskName);
        if (task == null) {
            if (projectId.isEmpty() || (projectId == null)) return null;
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
            if (userId.isEmpty() || (userId == null)) return null;
        }
        try {
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            return taskRepository.persist(newtask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if (taskId.isEmpty() || (taskId == null)) return;
        @Nullable final Task task = taskRepository.findOne(taskId);
        @Nullable final User user = userRepository.findOne(userId);
        if ((!userId.equals(task.getUserId())) || (!user.getRole().equals("admin"))) return;
        if (task != null) {
            if (taskName.isEmpty() || (taskName == null)) return;
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        try {
            taskRepository.merge(new Task(task.getProjectId(), taskName, description, dateStart, dateFinish, userId));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void remove(@Nullable final String taskId, @NotNull final String userId) {
        if (taskId.isEmpty() || (taskId == null)) return;
        @Nullable final Task task = taskRepository.findOne(taskId);
        @Nullable final User user = userRepository.findOne(userId);
        if ((!userId.equals(task.getUserId())) || (!user.getRole().equals("admin")))
            return;
        taskRepository.remove(taskId);
    }

    public void removeAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (!user.getRole().equals("admin")) return;
        taskRepository.removeAll();
    }

    public List<Task> findAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (!user.getRole().equals("admin")) return null;
        return taskRepository.findAll();
    }

    @Nullable
    public List<Task> sortedByStartDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if ((tasks.isEmpty()) || (tasks == null)) return null;
        return taskRepository.sortedByStartDate();
    }

    @Nullable
    public List<Task> sortedByFinishDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if ((tasks.isEmpty()) || (tasks == null)) return null;
        return taskRepository.sortedByFinishDate();
    }

    @Nullable
    public List<Task> sortedByStatus(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if ((tasks.isEmpty()) || (tasks == null)) return null;
        return taskRepository.sortedByStatus();
    }

    @Nullable
    public Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        if ((partOfName.isEmpty()) || (partOfName == null)) return null;
        return taskRepository.findByPartOfName(partOfName);
    }

    @Nullable
    public Task findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        if ((partOfDescription.isEmpty()) || (partOfDescription == null)) return null;
        return taskRepository.findByPartOfName(partOfDescription);
    }
}
