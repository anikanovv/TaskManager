package ru.anikanov.tm.service;

import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.User;

import java.text.ParseException;
import java.util.List;

public class ProjectService extends AbstractService implements IProjectService {
    private IProjectRepository projectRepository;
    private ITaskRepository taskRepository;
    private IUserRepository userRepository;

    public ProjectService(IProjectRepository pr, ITaskRepository tr, IUserRepository ur) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
    }

    public Project persist(String projectName, String description, String dateStart, String dateFinish, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return null;
        Project project = projectRepository.findOne(projectName);
        if (project == null) {
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
            if (userId.isEmpty() || (userId == null)) return null;
            try {
                Project newproject = new Project(projectName, description, dateStart, dateFinish, userId);
                return projectRepository.persist(newproject);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void merge(String projectName, String description, String dateStart, String dateFinish, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return;
        Project project = projectRepository.findOne(projectName);
        User user = userRepository.findOne(userId);
        if ((!userId.equals(project.getUserId())) || (!user.getRole().equals("admin")))
            return;
        if (project != null) {
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        try {
            Project p = new Project(projectName, description, dateStart, dateFinish, userId);
            projectRepository.merge(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(String projectName, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return;
        Project project = projectRepository.findOne(projectName);
        User user = userRepository.findOne(userId);
        if ((!userId.equals(project.getUserId())) || (!user.equals("admin")))
            return;
        if (project != null) {
            String projectId = project.getId();
            projectRepository.remove(projectName);
            taskRepository.removeWholeProject(projectId);
        }
    }

    public void removeAll(String userId) {
        User user = userRepository.findOne(userId);
        if (!user.getRole().equals("admin")) return;
        projectRepository.removeAll();
        taskRepository.removeAll();
    }

    public List<Project> findAll(String userId) {
        User user = userRepository.findOne(userId);
        if (!user.equals("admin")) return null;
        return projectRepository.findAll();
    }

    public Project findOne(String projectName, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return null;
        return projectRepository.findOne(projectName);
    }


}
