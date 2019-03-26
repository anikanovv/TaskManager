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
    public Task findOne(@Nullable final String taskId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((taskId == null) || taskId.isEmpty()) return null;
        Task task = taskRepository.findOne(taskId);
        if (userId.equals(Objects.requireNonNull(task).getUserId())) return task;
        else return null;
    }

    @Nullable
    public Task persist(@NotNull final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        @Nullable final Task task = taskRepository.findOne(taskName);
        if (task == null) {
            if (projectId.isEmpty()) return null;
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            if ((userId == null) || userId.isEmpty()) return null;
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
                      @Nullable final String dateStart, @Nullable final String dateFinish, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final Task task = taskRepository.findOne(taskId);
        @Nullable final User user = userRepository.findOne(userId);
        if (task == null) return;
        if (user == null) return;
        if (!userId.equals(task.getUserId()))
            return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        try {
            taskRepository.merge(taskName, description, dateStart, dateFinish, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@Nullable final String taskId, @Nullable final String userId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final Task task = taskRepository.findOne(taskId);
        @Nullable final User user = userRepository.findOne(userId);
        if (task == null) return;
        if (user == null) return;
        if (!userId.equals(task.getUserId())) return;
        taskRepository.remove(taskId);
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return;
        taskRepository.removeAll(userId);
    }

    public List<Task> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return taskRepository.findAll(userId);
    }

    @Nullable
    public List<Task> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return taskRepository.sortedByStartDate(userId);
    }

    @Nullable
    public List<Task> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return taskRepository.sortedByFinishDate(userId);
    }

    @Nullable
    public List<Task> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return taskRepository.sortedByStatus(userId);
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return taskRepository.findByPartOfName(partOfName, userId);
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return taskRepository.findByPartOfName(partOfDescription, userId);
    }
}
