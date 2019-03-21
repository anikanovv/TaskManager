package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;

import java.text.ParseException;
import java.util.List;

public class ProjectService extends AbstractService implements IProjectService {
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
    public Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final Session session) {
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

    public void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(userId);
        if (project == null) return;
        if (user == null) return;
        if ((!userId.equals(project.getUserId())) || (!user.getRole().displayName().equals("admin")))
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

    public void remove(@Nullable final String projectName, @NotNull final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(userId);
        if (project == null) return;
        if (user == null) return;
        if ((!userId.equals(project.getUserId())) || (!user.getRole().displayName().equals("admin")))
            return;
        @NotNull final String projectId = project.getId();
        projectRepository.remove(projectName);
        taskRepository.removeWholeProject(projectId);

    }

    public void removeAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (user == null) return;
        if (!user.getRole().displayName().equals("admin")) return;
        projectRepository.removeAll();
        taskRepository.removeAll();
    }

    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
//        if (!user.equals("admin")) return null;
        return projectRepository.findAll();
    }

    @Nullable
    public Project findOne(@Nullable final String projectName, @NotNull final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        return projectRepository.findOne(projectName);
    }

    @Nullable
    public List<Project> sortedByStartDate(@NotNull final String userId) throws Exception {
        @Nullable List<Project> projects = findAll(userId);
        if ((projects == null) || (projects.isEmpty())) return null;
        return projectRepository.sortedByStartDate();
    }

    @Nullable
    public List<Project> sortedByFinishDate(@NotNull final String userId) throws Exception {
        @Nullable List<Project> projects = findAll(userId);
        if ((projects == null) || (projects.isEmpty())) return null;
        return projectRepository.sortedByFinishDate();
    }

    @Nullable
    public List<Project> sortedByStatus(@NotNull final String userId) throws Exception {
        @Nullable List<Project> projects = findAll(userId);
        if ((projects == null) || (projects.isEmpty())) return null;
        return projectRepository.sortedByStatus();
    }

    @Nullable
    public Project findByPartOfName(@Nullable final String partOfName, @NotNull final String userId) throws Exception {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        return projectRepository.findByPartOfName(partOfName);
    }

    @Nullable
    public Project findByPartOfDescription(@Nullable final String partOfDescription, @NotNull final String userId) throws Exception {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        return projectRepository.findByPartOfName(partOfDescription);
    }


}
