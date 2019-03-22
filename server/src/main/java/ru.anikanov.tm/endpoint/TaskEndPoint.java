package ru.anikanov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
@WebService
public class TaskEndPoint {
    private ServiceLocator serviceLocator;
    public TaskEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    public TaskEndPoint() {
    }

    @Nullable
    @WebMethod
    public Task createTask(@WebParam String projectId, @WebParam String name, @WebParam String description, @WebParam String startDate, @WebParam String endDate, @WebParam @NotNull String userId) {
        return serviceLocator.getTaskService().persist(projectId, name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void updateTask(@WebParam String taskId, @WebParam String name, @WebParam String description, @WebParam String startDate, @WebParam String endDate, @WebParam @NotNull String userId) {
        serviceLocator.getTaskService().merge(taskId ,name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void removeTask(@WebParam String name, @WebParam @NotNull String userId) {
        serviceLocator.getTaskService().remove(name,userId);
    }
    @WebMethod
    public void removeAllTask(@WebParam @NotNull String userId) {
        serviceLocator.getTaskService().removeAll(userId);
    }
    @WebMethod
    @Nullable
    public List<Task> sortTaskByStartDate(@WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getTaskService().sortedByStartDate(userId);
    }

    @Nullable
    @WebMethod
    public List<Task> sortTaskByFinishDate(@WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getTaskService().sortedByFinishDate(userId);
    }

    @Nullable
    @WebMethod
    public List<Task> sortTaskByStatus(@WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getTaskService().sortedByStatus(userId);
    }

    @Nullable
    @WebMethod
    public List<Task> findAllTask(@WebParam @NotNull String userId) {
        return serviceLocator.getTaskService().findAll(userId);
    }

    @Nullable
    @WebMethod
    public Task findTaskByPartOfName(@WebParam @NotNull String partOfName, @WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getTaskService().findByPartOfName(partOfName,userId);
    }

    @Nullable
    @WebMethod
    public Task findTaskByPartOfDescription(@WebParam @NotNull String partOfDescription, @WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getTaskService().findByPartOfDescription(partOfDescription, userId);
    }
}
