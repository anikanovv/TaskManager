package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
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
    public Task createTask(@WebParam final String projectId, @WebParam final String name, @WebParam final String description,
                           @WebParam final String startDate, @WebParam final String endDate, @WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().persist(projectId, name, description, startDate, endDate, userId);
    }

    @WebMethod
    public void updateTask(@WebParam final String taskId, @WebParam final String name, @WebParam final String description,
                           @WebParam final String startDate, @WebParam final String endDate, @WebParam @NotNull final String userId) {
        serviceLocator.getTaskService().merge(taskId ,name, description, startDate, endDate, userId);
    }

    @WebMethod
    public void removeTask(@WebParam final String name, @WebParam @NotNull final String userId) {
        serviceLocator.getTaskService().remove(name,userId);
    }

    @WebMethod
    public void removeAllTask(@WebParam @NotNull final String userId) {
        serviceLocator.getTaskService().removeAll(userId);
    }

    @WebMethod
    @Nullable
    public List<Task> sortTaskByStartDate(@WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().sortedByStartDate(userId);
    }

    @Nullable
    @WebMethod
    public List<Task> sortTaskByFinishDate(@WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().sortedByFinishDate(userId);
    }

    @Nullable
    @WebMethod
    public List<Task> sortTaskByStatus(@WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().sortedByStatus(userId);
    }

    @Nullable
    @WebMethod
    public List<Task> findAllTask(@WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().findAll(userId);
    }

    @Nullable
    @WebMethod
    public Task findTaskByPartOfName(@WebParam @NotNull final String partOfName, @WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().findByPartOfName(partOfName,userId);
    }

    @Nullable
    @WebMethod
    public Task findTaskByPartOfDescription(@WebParam @NotNull final String partOfDescription, @WebParam @NotNull final String userId) {
        return serviceLocator.getTaskService().findByPartOfDescription(partOfDescription, userId);
    }
}
