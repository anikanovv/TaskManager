package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.repository.TaskRepository;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TaskService implements ITaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Nullable
    public Task persist(@Nullable final String userId, @Nullable final String projectId, @Nullable final String taskName, @Nullable final String description,
                        @Nullable final String dateStart, @Nullable final String dateFinish) {
        if ((taskName == null) || taskName.isEmpty()) return null;
        if ((userId == null) || userId.isEmpty()) return null;
            if (projectId == null || projectId.isEmpty()) return null;
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            @NotNull final Task newtask = new Task(projectId, taskName, description, dateStart, dateFinish, userId);
        taskRepository.save(newtask);
            return newtask;
    }

    public void merge(@Nullable final String userId, @Nullable final String taskId, @Nullable final String taskName, @Nullable final String description,
                      @Nullable final String dateStart, @Nullable final String dateFinish) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        if ((taskName == null) || taskName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        @Nullable Task task = taskRepository.findByUserIdAndName(userId, taskId);
            if (task == null) task = new Task();
            task.setName(taskName);
            task.setDescription(description);
            task.setStart(dateStart);
            task.setEnd(dateFinish);
        taskRepository.save(task);
    }

    public void remove(@Nullable final String userId, @Nullable final String taskId) {
        if ((taskId == null) || taskId.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final Task task = taskRepository.findByUserIdAndId(userId, taskId);
            if (task == null) return;
        taskRepository.delete(task);
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        taskRepository.deleteAllByUserId(userId);
    }

    @Nullable
    public Task findOne(@Nullable final String userId, @Nullable final String taskId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((taskId == null) || taskId.isEmpty()) return null;
        return taskRepository.findByUserIdAndId(userId, taskId);
    }

    @Nullable
    public Task findByPartOfName(@Nullable final String userId, @Nullable final String partOfName) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return taskRepository.findByUserIdAndName(userId, partOfName);
    }

    @Nullable
    public Task findByPartOfDescription(@Nullable final String userId, @Nullable final String partOfDescription) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return taskRepository.findByUserIdAndDescription(userId, partOfDescription);
    }

    public List<Task> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        return taskRepository.findAllByUserId(userId);
    }

    @Nullable
    public List<Task> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Task> tasks = taskRepository.findAllByUserId(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Task> tasks = taskRepository.findAllByUserId(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Task> tasks = taskRepository.findAllByUserId(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }
}
