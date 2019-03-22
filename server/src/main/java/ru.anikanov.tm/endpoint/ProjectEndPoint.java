package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IProjectEndPoint;
import ru.anikanov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
@WebService
@NoArgsConstructor
public class ProjectEndPoint implements IProjectEndPoint {
    private ServiceLocator serviceLocator;

    public ProjectEndPoint(ServiceLocator serviceLocator) {
        this.serviceLocator=serviceLocator;
    }

    @WebMethod
    public Project createProject(@WebParam final String name, @WebParam final String description, @WebParam final String startDate,
                                 @WebParam final String endDate, @WebParam @NotNull final String userId) {
        return serviceLocator.getProjectService()
                .persist(name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void updateProject(@WebParam final String name, @WebParam final String description, @WebParam final String startDate,
                              @WebParam final String endDate, @WebParam @NotNull final String userId) {
       serviceLocator.getProjectService().merge(name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void removeProject(@WebParam final String name, @WebParam @NotNull final String userId) {
        serviceLocator.getProjectService().remove(name,userId);
    }
    @WebMethod
    public void removeAllProject(@WebParam @NotNull final String userId) {
        serviceLocator.getProjectService().removeAll(userId);
    }
    @WebMethod
    public Project findProjectByPartOfNameProject(@WebParam final String partOfName, @WebParam @NotNull final String userId) throws Exception {
        return serviceLocator.getProjectService().findByPartOfName(partOfName,userId);
    }
    @WebMethod
    public Project findProjectByPartOfDescription(@WebParam final String partOfDescription, @WebParam @NotNull final String userId) throws Exception {
        return serviceLocator.getProjectService().findByPartOfDescription(partOfDescription, userId);
    }
    @WebMethod
    public List<Project> sortProjectByStartDate(@WebParam @NotNull final String userId) throws Exception {
        return serviceLocator.getProjectService().sortedByStartDate(userId);
    }
    @WebMethod
    public List<Project> sortProjectByFinishDate(@WebParam @NotNull final String userId) throws Exception {
        return serviceLocator.getProjectService().sortedByFinishDate(userId);
    }
    @WebMethod
    public List<Project> sortProjectByStatus(@WebParam @NotNull final String userId) throws Exception {
        return serviceLocator.getProjectService().sortedByStatus(userId);
    }
    @WebMethod
    public List<Project> findAllProject(@WebParam @NotNull final String userId) {
        return serviceLocator.getProjectService().findAll(userId);
    }
}
