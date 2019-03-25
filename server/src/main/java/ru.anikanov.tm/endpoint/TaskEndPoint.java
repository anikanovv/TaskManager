package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@NoArgsConstructor
@WebService
public class TaskEndPoint {
    private ServiceLocator serviceLocator;

    public TaskEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    @Nullable
    @WebMethod
    public Task createTask(@WebParam @NotNull final Session session, @WebParam final String projectId, @WebParam final String name, @WebParam final String description,
                           @WebParam final String startDate, @WebParam final String endDate) {
        return serviceLocator.getTaskService().persist(projectId, name, description, startDate, endDate, session.getUserId());
    }

    @WebMethod
    public void updateTask(@WebParam @NotNull final Session session, @WebParam final String taskId, @WebParam final String name, @WebParam final String description,
                           @WebParam final String startDate, @WebParam final String endDate) {
        serviceLocator.getTaskService().merge(taskId, name, description, startDate, endDate, session.getUserId());
    }

    @WebMethod
    public void removeTask(@WebParam @NotNull final Session session, @WebParam final String name) {
        serviceLocator.getTaskService().remove(name, session.getUserId());
    }

    @WebMethod
    public void removeAllTask(@WebParam @NotNull final Session session) {
        serviceLocator.getTaskService().removeAll(session.getUserId());
    }

    @WebMethod
    @Nullable
    public List<Task> sortTaskByStartDate(@WebParam @NotNull final Session session) {
        return serviceLocator.getTaskService().sortedByStartDate(session.getUserId());
    }

    @Nullable
    @WebMethod
    public List<Task> sortTaskByFinishDate(@WebParam @NotNull final Session session) {
        return serviceLocator.getTaskService().sortedByFinishDate(session.getUserId());
    }

    @Nullable
    @WebMethod
    public List<Task> sortTaskByStatus(@WebParam @NotNull final Session session) {
        return serviceLocator.getTaskService().sortedByStatus(session.getUserId());
    }

    @Nullable
    @WebMethod
    public List<Task> findAllTask(@WebParam @NotNull final Session session) {
        return serviceLocator.getTaskService().findAll(session.getUserId());
    }

    @Nullable
    @WebMethod
    public Task findTaskByPartOfName(@WebParam @NotNull final Session session, @WebParam @NotNull final String partOfName) {
        return serviceLocator.getTaskService().findByPartOfName(partOfName, session.getUserId());
    }

    @Nullable
    @WebMethod
    public Task findTaskByPartOfDescription(@WebParam @NotNull final Session session, @WebParam @NotNull final String partOfDescription) {
        return serviceLocator.getTaskService().findByPartOfDescription(partOfDescription, session.getUserId());
    }
}
