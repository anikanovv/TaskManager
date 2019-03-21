package ru.anikanov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IProjectEndPoint;
import ru.anikanov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
@WebService
public class ProjectEndPoint {
    private ServiceLocator serviceLocator;
    public ProjectEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    public ProjectEndPoint() {
    }
    @WebMethod
    public Project createProject(@WebParam String name, @WebParam String description, @WebParam String startDate, @WebParam String endDate, @WebParam @NotNull String userId) {
        return serviceLocator.getProjectService().persist(name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void updateProject(@WebParam String name, @WebParam String description, @WebParam String startDate, @WebParam String endDate, @WebParam @NotNull String userId) {
       serviceLocator.getProjectService().merge(name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void removeProject(@WebParam String name, @WebParam @NotNull String userId) {
        serviceLocator.getProjectService().remove(name,userId);
    }
    @WebMethod
    public void removeAllProject(@WebParam @NotNull String userId) {
        serviceLocator.getProjectService().removeAll(userId);
    }
    @WebMethod
    public Project findProjectByPartOfNameProject(@WebParam String partOfName, @WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getProjectService().findByPartOfName(partOfName,userId);
    }
    @WebMethod
    public Project findProjectByPartOfDescription(@WebParam String partOfDescription, @WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getProjectService().findByPartOfDescription(partOfDescription, userId);
    }
    @WebMethod
    public List<Project> sortProjectByStartDate(@WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getProjectService().sortedByStartDate(userId);
    }
    @WebMethod
    public List<Project> sortProjectByFinishDate(@WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getProjectService().sortedByFinishDate(userId);
    }
    @WebMethod
    public List<Project> sortProjectByStatus(@WebParam @NotNull String userId) throws Exception {
        return serviceLocator.getProjectService().sortedByStatus(userId);
    }
    @WebMethod
    public List<Project> findAllProject(@WebParam @NotNull String userId) {
        return serviceLocator.getProjectService().findAll(userId);
    }
}
