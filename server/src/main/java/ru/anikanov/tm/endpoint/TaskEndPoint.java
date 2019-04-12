package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.api.service.ITaskService;
import ru.anikanov.tm.dto.TaskDto;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.Task;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
@NoArgsConstructor
public class TaskEndPoint {
    @Inject
    private ISessionService sessionService;
    @Inject
    private ITaskService taskService;

    @Nullable
    @WebMethod
    public TaskDto createTask(@WebParam @NotNull final Session session, @WebParam final String projectId, @WebParam final String name, @WebParam final String description,
                              @WebParam final String startDate, @WebParam final String endDate) {
        sessionService.validate(session);
        Task task = taskService.persist(projectId, name, description, startDate, endDate, Objects.requireNonNull(session.getUserId()));
        return new TaskDto(task);
    }

    @WebMethod
    public void updateTask(@WebParam @NotNull final Session session, @WebParam final String taskId, @WebParam final String name, @WebParam final String description,
                           @WebParam final String startDate, @WebParam final String endDate) {
        sessionService.validate(session);
        taskService
                .merge(taskId, name, description, startDate, endDate, Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeTask(@WebParam @NotNull final Session session, @WebParam final String name) {
        sessionService.validate(session);
        taskService
                .remove(name, Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeAllTask(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        taskService
                .removeAll(Objects.requireNonNull(session.getUserId()));
    }

    @Nullable
    @WebMethod
    public List<TaskDto> sortTaskByStartDate(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = taskService.sortedByStartDate(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> sortTaskByFinishDate(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = taskService.sortedByFinishDate(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> sortTaskByStatus(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = taskService.sortedByStatus(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> findAllTask(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = taskService.findAll(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public TaskDto findTaskByPartOfName(@WebParam @NotNull final Session session, @WebParam @NotNull final String partOfName) {
        sessionService.validate(session);
        Task task = taskService.findByPartOfName(partOfName, Objects.requireNonNull(session.getUserId()));
        return new TaskDto(task);
    }

    @Nullable
    @WebMethod
    public TaskDto findTaskByPartOfDescription(@WebParam @NotNull final Session session, @WebParam @NotNull final String partOfDescription) {
        sessionService.validate(session);
        Task task = taskService.findByPartOfDescription(partOfDescription, Objects.requireNonNull(session.getUserId()));
        return new TaskDto(task);
    }
}
