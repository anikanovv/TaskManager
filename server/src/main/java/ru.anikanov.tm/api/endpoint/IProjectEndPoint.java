package ru.anikanov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndPoint {
    @WebMethod
    Project createProject(@WebParam final String name, @WebParam final String description,
                          @WebParam final String startDate, @WebParam final String endDate, @WebParam @NotNull final Session session);
    @WebMethod
    void updateProject(@WebParam final String name, @WebParam final String description,
                       @WebParam final String startDate, @WebParam final String endDate, @WebParam @NotNull final Session session);
    @WebMethod
    void removeProject(@WebParam final String name, @WebParam @NotNull final Session session);
    @WebMethod
    void removeAllProject(@WebParam @NotNull final Session session);
    @WebMethod
    Project findProjectByPartOfNameProject(@WebParam final String partOfName, @WebParam @NotNull final Session session);
    @WebMethod
    Project findProjectByPartOfDescription(@WebParam final String partOfDescription, @WebParam @NotNull final Session session);
    @WebMethod
    List<Project> sortProjectByStartDate(@WebParam @NotNull final Session session);
    @WebMethod
    List<Project> sortProjectByFinishDate(@WebParam @NotNull final Session session);
    @WebMethod
    List<Project> sortProjectByStatus(@WebParam @NotNull final Session session);
    @WebMethod
    List<Project> findAllProject(@WebParam @NotNull final Session session);
}
