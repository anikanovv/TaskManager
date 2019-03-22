package ru.anikanov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndPoint {
    @WebMethod
    Project createProject(@WebParam final String name, @WebParam final String description, @WebParam final String startDate, @WebParam final String endDate, @WebParam @NotNull final String userId);
    @WebMethod
    void updateProject(@WebParam final String name, @WebParam final String description, @WebParam final String startDate, @WebParam final String endDate, @WebParam @NotNull final String userId);
    @WebMethod
    void removeProject(@WebParam final String name, @WebParam @NotNull final String userId);
    @WebMethod
    void removeAllProject(@WebParam @NotNull final String userId);
    @WebMethod
    Project findProjectByPartOfNameProject(@WebParam final String partOfName, @WebParam @NotNull final String userId) throws Exception;
    @WebMethod
    Project findProjectByPartOfDescription(@WebParam final String partOfDescription, @WebParam @NotNull final String userId) throws Exception;
    @WebMethod
    List<Project> sortProjectByStartDate(@WebParam @NotNull final String userId) throws Exception;
    @WebMethod
    List<Project> sortProjectByFinishDate(@WebParam @NotNull final String userId) throws Exception;
    @WebMethod
    List<Project> sortProjectByStatus(@WebParam @NotNull final String userId) throws Exception;
    @WebMethod
    List<Project> findAllProject(@WebParam @NotNull final String userId);
}
