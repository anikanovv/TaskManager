package ru.anikanov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndPoint {
    @WebMethod
    Project createProject(final String name, final String description, final String startDate, final String endDate, @NotNull final String userId);
    @WebMethod
    void updateProject(final String name, final String description, final String startDate, final String endDate, @NotNull final String userId);
    @WebMethod
    void removeProject(final String name, @NotNull final String userId);
    @WebMethod
    void removeAllProject(@NotNull final String userId);
    @WebMethod
    Project findProjectByPartOfNameProject(final String partOfName, @NotNull final String userId);
    @WebMethod
    Project findProjectByPartOfDescription(final String partOfDescription, @NotNull final String userId);
    @WebMethod
    List<Project> sortProjectByStartDate(@NotNull final String userId);
    @WebMethod
    List<Project> sortProjectByFinishDate(@NotNull final String userId);
    @WebMethod
    List<Project> sortProjectByStatus(@NotNull final String userId);
    @WebMethod
    List<Project> findAllProject(@NotNull final String userId);
}
