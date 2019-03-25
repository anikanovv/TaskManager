package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IProjectEndPoint;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;

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
    public Project createProject(@WebParam @NotNull final Session session, @WebParam final String name, @WebParam final String description, @WebParam final String startDate,
                                 @WebParam final String endDate) {
        return serviceLocator.getProjectService()
                .persist(name, description, startDate, endDate, session);
    }
    @WebMethod
    public void updateProject(@WebParam @NotNull final Session session, @WebParam final String name, @WebParam final String description, @WebParam final String startDate,
                              @WebParam final String endDate) {
        serviceLocator.getProjectService().merge(name, description, startDate, endDate, session);
    }
    @WebMethod
    public void removeProject(@WebParam @NotNull final Session session, @WebParam final String name) {
        serviceLocator.getProjectService().remove(name, session);
    }
    @WebMethod
    public void removeAllProject(@WebParam @NotNull final Session session) {
        serviceLocator.getProjectService().removeAll(session);
    }
    @WebMethod
    public Project findProjectByPartOfNameProject(@WebParam @NotNull final Session session, @WebParam final String partOfName) {
        return serviceLocator.getProjectService().findByPartOfName(partOfName, session);
    }
    @WebMethod
    public Project findProjectByPartOfDescription(@WebParam @NotNull final Session session, @WebParam final String partOfDescription) {
        return serviceLocator.getProjectService().findByPartOfDescription(partOfDescription, session);
    }
    @WebMethod
    public List<Project> sortProjectByStartDate(@WebParam @NotNull final Session session) {
        return serviceLocator.getProjectService().sortedByStartDate(session);
    }
    @WebMethod
    public List<Project> sortProjectByFinishDate(@WebParam @NotNull final Session session) {
        return serviceLocator.getProjectService().sortedByFinishDate(session);
    }
    @WebMethod
    public List<Project> sortProjectByStatus(@WebParam @NotNull final Session session) {
        return serviceLocator.getProjectService().sortedByStatus(session);
    }
    @WebMethod
    public List<Project> findAllProject(@WebParam @NotNull final Session session) {
        return serviceLocator.getProjectService().findAll(session);
    }
}
