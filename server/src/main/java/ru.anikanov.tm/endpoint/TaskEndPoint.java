package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.dto.TaskDto;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@WebService
public class TaskEndPoint {
    private ServiceLocator serviceLocator;

    public TaskEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    @Nullable
    @WebMethod
    public TaskDto createTask(@WebParam @NotNull final Session session, @WebParam final String projectId, @WebParam final String name, @WebParam final String description,
                              @WebParam final String startDate, @WebParam final String endDate) {
        serviceLocator.getSessionService().validate(session);
        Task task = serviceLocator.getTaskService().persist(projectId, name, description, startDate, endDate, Objects.requireNonNull(session.getUserId()));
        return new TaskDto(task);
    }

    @WebMethod
    public void updateTask(@WebParam @NotNull final Session session, @WebParam final String taskId, @WebParam final String name, @WebParam final String description,
                           @WebParam final String startDate, @WebParam final String endDate) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService()
                .merge(taskId, name, description, startDate, endDate, Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeTask(@WebParam @NotNull final Session session, @WebParam final String name) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService()
                .remove(name, Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeAllTask(@WebParam @NotNull final Session session) {
        serviceLocator.getSessionService().validate(session);
        serviceLocator.getTaskService()
                .removeAll(Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    @Nullable
    public List<TaskDto> sortTaskByStartDate(@WebParam @NotNull final Session session) {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortedByStartDate(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> sortTaskByFinishDate(@WebParam @NotNull final Session session) {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortedByFinishDate(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> sortTaskByStatus(@WebParam @NotNull final Session session) {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().sortedByStatus(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public List<TaskDto> findAllTask(@WebParam @NotNull final Session session) {
        serviceLocator.getSessionService().validate(session);
        @NotNull final List<TaskDto> listDto = new ArrayList<>();
        @Nullable final List<Task> list = serviceLocator.getTaskService().findAll(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new TaskDto(v)));
        return listDto;
    }

    @Nullable
    @WebMethod
    public TaskDto findTaskByPartOfName(@WebParam @NotNull final Session session, @WebParam @NotNull final String partOfName) {
        serviceLocator.getSessionService().validate(session);
        Task task = serviceLocator.getTaskService().findByPartOfName(partOfName, Objects.requireNonNull(session.getUserId()));
        return new TaskDto(task);
    }

    @Nullable
    @WebMethod
    public TaskDto findTaskByPartOfDescription(@WebParam @NotNull final Session session, @WebParam @NotNull final String partOfDescription) {
        serviceLocator.getSessionService().validate(session);
        Task task = serviceLocator.getTaskService().findByPartOfDescription(partOfDescription, Objects.requireNonNull(session.getUserId()));
        return new TaskDto(task);
    }
}
