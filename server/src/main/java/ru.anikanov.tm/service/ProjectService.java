package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;

import java.util.ArrayList;
import java.util.Comparator;
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
                           @Nullable final String dateFinish, @Nullable final Session session) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        @Nullable final Project project = projectRepository.findOne(projectName);
        if (project == null) {
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            if ((session == null) || (session.getUserId() == null) || session.getUserId().isEmpty()) return null;
            try {
                @Nullable final Project newproject = new Project(projectName, description, dateStart, dateFinish, session.getUserId());
                return projectRepository.persist(newproject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                      @Nullable final String dateFinish, @NotNull final Session session) {
        if ((projectName == null) || projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(Objects.requireNonNull(session.getUserId()));
        if (project == null) return;
        if (user == null) return;
        if ((!session.getUserId().equals(project.getUserId())) || (!Objects.requireNonNull(user.getRole()).displayName().equals("admin")))
            return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        try {
            @Nullable final Project p = new Project(projectName, description, dateStart, dateFinish, session.getUserId());
            projectRepository.merge(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@Nullable final String projectName, @Nullable final Session session) {
        if ((projectName == null) || projectName.isEmpty()) return;
        if ((session == null) || (session.getUserId() == null) || session.getUserId().isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(session.getUserId());
        if (project == null) return;
        if (user == null) return;
        if ((!session.getUserId().equals(project.getUserId())) || (!Objects.equals(Objects.requireNonNull(user.getRole()).displayName(), "admin")))
            return;
        @NotNull final String projectId = project.getId();
        projectRepository.remove(projectName);
        taskRepository.removeWholeProject(projectId);

    }

    public void removeAll(@NotNull final Session session) {
        if ((session.getUserId() == null) || session.getUserId().isEmpty()) return;
        @Nullable final User user = userRepository.findOne(session.getUserId());
        if (user == null) return;
        if (user.getRole() == null) return;
        if (!user.getRole().displayName().equals("admin")) return;
        projectRepository.removeAll();
        taskRepository.removeAll();
    }

    @Nullable
    public List<Project> findAll(@NotNull final Session session) {
        @Nullable final User user = userRepository.findOne(Objects.requireNonNull(session.getUserId()));
        List<Project> projects = projectRepository.findAll();
        List<Project> newProjects = new ArrayList<>();
        if (projects == null) return null;
        projects.forEach(project -> {
            if (Objects.requireNonNull(project.getUserId()).equals(Objects.requireNonNull(user).getId()))
                newProjects.add(project);
        });
        return newProjects;
    }

    @Nullable
    public Project findOne(@Nullable final String projectName, @NotNull final Session session) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        Project project = projectRepository.findOne(projectName);
        if (project.getUserId().equals(session.getUserId())) return project;
        else return null;
    }

    @Nullable
    public List<Project> sortedByStartDate(@NotNull final Session session) {
        @Nullable List<Project> projects = findAll(session);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStartDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate(@NotNull final Session session) {
        @Nullable List<Project> projects = findAll(session);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getEndDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus(@NotNull final Session session) {
        @Nullable List<Project> projects = findAll(session);
        if ((projects == null) || (projects.isEmpty())) return null;
        projects.sort(Comparator.comparing(Project::getStatus));
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@Nullable final String partOfName, @NotNull final Session session) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        return projectRepository.findByPartOfName(partOfName);
    }

    @Nullable
    public Project findByPartOfDescription(@Nullable final String partOfDescription, @NotNull final Session session) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        return projectRepository.findByPartOfName(partOfDescription);
    }


}
