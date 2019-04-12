package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.dto.ProjectDto;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebService
@NoArgsConstructor
public class ProjectEndPoint {
    @Inject
    private ISessionService sessionService;
    @Inject
    private IProjectService projectService;

    @WebMethod
    public ProjectDto createProject(@WebParam @NotNull final Session session, @WebParam final String name, @WebParam final String description, @WebParam final String startDate,
                                    @WebParam final String endDate) {
        sessionService.validate(session);
        Project project = projectService
                .persist(name, description, startDate, endDate, Objects.requireNonNull(session.getUserId()));
        return new ProjectDto(project);
    }

    @WebMethod
    public void updateProject(@WebParam @NotNull final Session session, @WebParam final String id, @WebParam final String name,
                              @WebParam final String description, @WebParam final String startDate,
                              @WebParam final String endDate) {
        sessionService.validate(session);
        projectService.merge(id, name, description, startDate, endDate, Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeProject(@WebParam @NotNull final Session session, @WebParam final String name) {
        sessionService.validate(session);
        projectService.remove(name, Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeAllProject(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        projectService.removeAll(Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public ProjectDto findProjectByPartOfNameProject(@WebParam @NotNull final Session session, @WebParam final String partOfName) {
        sessionService.validate(session);
        Project project = projectService.findByPartOfName(partOfName, Objects.requireNonNull(session.getUserId()));
        return new ProjectDto(project);
    }

    @WebMethod
    public ProjectDto findProjectByPartOfDescription(@WebParam @NotNull final Session session, @WebParam final String partOfDescription) {
        sessionService.validate(session);
        Project project = projectService.findByPartOfDescription(partOfDescription, Objects.requireNonNull(session.getUserId()));
        return new ProjectDto(project);
    }

    @WebMethod
    public List<ProjectDto> findAllProject(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.findAll(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @WebMethod
    public List<ProjectDto> sortProjectByStartDate(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortedByStartDate(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @WebMethod
    public List<ProjectDto> sortProjectByFinishDate(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortedByFinishDate(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }

    @WebMethod
    public List<ProjectDto> sortProjectByStatus(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<ProjectDto> listDto = new ArrayList<>();
        @Nullable final List<Project> list = projectService.sortedByStatus(session.getUserId());
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new ProjectDto(v)));
        return listDto;
    }
}
