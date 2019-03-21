package ru.anikanov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.ITaskEndPoint;
import ru.anikanov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;
@WebService
public class TaskEndPoint implements ITaskEndPoint {
    private ServiceLocator serviceLocator;
    public TaskEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }
    @WebMethod
    public Task createTask(String projectId, String name, String description, String startDate, String endDate, @NotNull String userId) {
        return serviceLocator.getTaskService().persist(projectId, name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void updateTask(String taskId, String name, String description, String startDate, String endDate, @NotNull String userId) {
        serviceLocator.getTaskService().merge(taskId ,name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void removeTask(String name, @NotNull String userId) {
        serviceLocator.getTaskService().remove(name,userId);
    }
    @WebMethod
    public void removeAllTask(@NotNull String userId) {
        serviceLocator.getTaskService().removeAll(userId);
    }
    @WebMethod
    public List<Task> sortTaskByStartDate(@NotNull String userId) {
        return serviceLocator.getTaskService().sortedByStartDate(userId);
    }
    @WebMethod
    public List<Task> sortTaskByFinishDate(@NotNull String userId) {
        return serviceLocator.getTaskService().sortedByFinishDate(userId);
    }
    @WebMethod
    public List<Task> sortTaskByStatus(@NotNull String userId) {
        return serviceLocator.getTaskService().sortedByStatus(userId);
    }
    @WebMethod
    public List<Task> findAllTask(@NotNull String userId) {
        return serviceLocator.getTaskService().findAll(userId);
    }
    @WebMethod
    public Task findTaskByPartOfName(@NotNull String partOfName, @NotNull String userId) {
        return serviceLocator.getTaskService().findByPartOfName(partOfName,userId);
    }

    @WebMethod
    public Task findTaskByPartOfDescription(@NotNull String partOfDescription, @NotNull String userId) {
        return serviceLocator.getTaskService().findByPartOfDescription(partOfDescription, userId);
    }
}
