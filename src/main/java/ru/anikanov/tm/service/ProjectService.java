package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.Project;
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
    public Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if (projectName.isEmpty() || (projectName == null)) return null;
        @Nullable final Project project = projectRepository.findOne(projectName);
        if (project == null) {
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
            if (userId.isEmpty() || (userId == null)) return null;
            try {
                @Nullable final Project newproject = new Project(projectName, description, dateStart, dateFinish, userId);
                return projectRepository.persist(newproject);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId) {
        if (projectName.isEmpty() || (projectName == null)) return;
        @Nullable Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(userId);
        if ((!userId.equals(project.getUserId())) || (!user.getRole().equals("admin")))
            return;
        if (project != null) {
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        try {
            @Nullable Project p = new Project(projectName, description, dateStart, dateFinish, userId);
            projectRepository.merge(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(@Nullable final String projectName, @Nullable final String userId) {
        if (projectName.isEmpty() || (projectName == null)) return;
        @Nullable final Project project = projectRepository.findOne(projectName);
        @Nullable final User user = userRepository.findOne(userId);
        if ((!userId.equals(project.getUserId())) || (!user.equals("admin")))
            return;
        if (project != null) {
            @Nullable final String projectId = project.getId();
            projectRepository.remove(projectName);
            taskRepository.removeWholeProject(projectId);
        }
    }

    public void removeAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (!user.getRole().equals("admin")) return;
        projectRepository.removeAll();
        taskRepository.removeAll();
    }

    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        @Nullable final User user = userRepository.findOne(userId);
        if (!user.equals("admin")) return null;
        return projectRepository.findAll();
    }

    @Nullable
    public Project findOne(@Nullable final String projectName, @NotNull final String userId) {
        if (projectName.isEmpty() || (projectName == null)) return null;
        return projectRepository.findOne(projectName);
    }


}
