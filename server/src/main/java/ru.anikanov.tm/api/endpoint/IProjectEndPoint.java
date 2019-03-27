package ru.anikanov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebService
public interface IProjectEndPoint {
    @WebMethod
    Project createProject(@WebParam @NotNull final Session session, @WebParam final String name, @WebParam final String description,
                          @WebParam final String startDate, @WebParam final String endDate);
    @WebMethod
    void updateProject(@WebParam @NotNull final Session session, @WebParam final String name, @WebParam final String description,
                       @WebParam final String startDate, @WebParam final String endDate);
    @WebMethod
    void removeProject(@WebParam @NotNull final Session session, @WebParam final String name);
    @WebMethod
    void removeAllProject(@WebParam @NotNull final Session session);
    @WebMethod
    Project findProjectByPartOfNameProject(@WebParam @NotNull final Session session, @WebParam final String partOfName) throws SQLException, ParseException;
    @WebMethod
    Project findProjectByPartOfDescription(@WebParam @NotNull final Session session, @WebParam final String partOfDescription);
    @WebMethod
    List<Project> sortProjectByStartDate(@WebParam @NotNull final Session session);
    @WebMethod
    List<Project> sortProjectByFinishDate(@WebParam @NotNull final Session session);
    @WebMethod
    List<Project> sortProjectByStatus(@WebParam @NotNull final Session session);
    @WebMethod
    List<Project> findAllProject(@WebParam @NotNull final Session session);
}
