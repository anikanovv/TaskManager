package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class ProjectService implements IProjectService {
    @NotNull
    private IProjectRepository projectRepository;
    @NotNull
    private ITaskRepository taskRepository;
    @NotNull
    private IUserRepository userRepository;

    public ProjectService(@NotNull final IProjectRepository pr, @NotNull final ITaskRepository tr, @NotNull final IUserRepository ur) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
    }

    @Nullable
    public Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                           @Nullable final String dateFinish, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        @Nullable final Project project = projectRepository.findOne(projectName);
        if (project == null) {
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            if ((userId == null) || userId.isEmpty()) return null;
            try {
                @Nullable final Project newproject = new Project(projectName, description, dateStart, dateFinish, userId);
                return projectRepository.persist(newproject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                      @Nullable final String dateFinish, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(Objects.requireNonNull(userId));
        if (project == null) return;
        if (user == null) return;
        if (!userId.equals(project.getUserId()))
            return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        try {
            @Nullable final Project p = new Project(projectName, description, dateStart, dateFinish, userId);
            projectRepository.merge(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@Nullable final String projectName, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(userId);
        if (project == null) return;
        if (user == null) return;
        if (!userId.equals(project.getUserId()))
            return;
        @NotNull final String projectId = project.getId();
        projectRepository.remove(projectName);
        taskRepository.removeWholeProject(projectId);

    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return;
        taskRepository.removeAll(userId);
        projectRepository.removeAll(userId);
    }

    @Nullable
    public List<Project> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return null;
        return projectRepository.findAll(userId);
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((projectId == null) || projectId.isEmpty()) return null;
        Project project = projectRepository.findOne(projectId);
        if (project == null) return null;
        if (userId.equals(project.getUserId())) return project;
        else return null;
    }

    @Nullable
    public List<Project> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.sortedByStartDate(userId);
    }

    @Nullable
    public List<Project> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.sortedByFinishDate(userId);
    }

    @Nullable
    public List<Project> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.sortedByStatus(userId);
    }

    @Nullable
    public Project findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) throws SQLException, ParseException {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.findByPartOfName(partOfName, userId);
    }

    @Nullable
    public Project findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.findByPartOfDescription(partOfDescription, userId);
    }


}
