package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.entity.User;

import java.util.List;
import java.util.Objects;

public class TaskService extends AbstractService implements ITaskService {
    @NotNull
    private IProjectRepository projectRepository;
    @NotNull
    private ITaskRepository taskRepository;
    @NotNull
    private IUserRepository userRepository;

    public TaskService(@NotNull final IProjectRepository pr, @NotNull final ITaskRepository tr, @NotNull final IUserRepository ur) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
    }

    @Nullable
    public Task findOne(@Nullable final String taskId, @NotNull final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return null;
        return taskRepository.findOne(taskId);
    }

    @Nullable
    public Task persist(@NotNull final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        @Nullable final Task task = taskRepository.findOne(taskName);
        if (task == null) {
            if (projectId.isEmpty()) return null;
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            if (userId.isEmpty()) return null;
        }
        try {
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
            return taskRepository.persist(newtask);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void merge(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        @Nullable final Task task = taskRepository.findOne(taskId);
        @Nullable final User user = userRepository.findOne(userId);
        if (task == null) return;
        if (user == null) return;
        if ((!userId.equals(task.getUserId())) || (!Objects.requireNonNull(user.getRole()).displayName().equals("admin")))
            return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        try {
            taskRepository.merge(new Task(task.getProjectId(), taskName, description, dateStart, dateFinish, userId));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@Nullable final String taskId, @NotNull final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        @Nullable final Task task = taskRepository.findOne(taskId);
        @Nullable final User user = userRepository.findOne(userId);
        if (task == null) return;
        if (user == null) return;
        if ((!userId.equals(task.getUserId())) || (!Objects.requireNonNull(user.getRole()).displayName().equals("admin")))
            return;
        taskRepository.remove(taskId);
    }

    public void removeAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return;
        if (!Objects.requireNonNull(user.getRole()).displayName().equals("admin")) return;
        taskRepository.removeAll();
    }

    public List<Task> findAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        if (!Objects.requireNonNull(user.getRole()).displayName().equals("admin")) return null;
        return taskRepository.findAll();
    }

    @Nullable
    public List<Task> sortedByStartDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if ((tasks == null) || (tasks.isEmpty())) return null;
        return taskRepository.sortedByStartDate();
    }

    @Nullable
    public List<Task> sortedByFinishDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if ((tasks == null) || (tasks.isEmpty())) return null;
        return taskRepository.sortedByFinishDate();
    }

    @Nullable
    public List<Task> sortedByStatus(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if ((tasks == null) || (tasks.isEmpty())) return null;
        return taskRepository.sortedByStatus();
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String partOfName, @NotNull final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        return taskRepository.findByPartOfName(partOfName);
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String partOfDescription, @NotNull final String userId) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        return taskRepository.findByPartOfName(partOfDescription);
    }
}
