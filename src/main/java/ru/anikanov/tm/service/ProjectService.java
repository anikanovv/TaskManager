package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;
import ru.anikanov.tm.repository.UserRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService implements ProjectServiceInterface {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository pr, TaskRepository tr, UserRepository ur) {
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
                return (projectRepository.persist(projectName, description, dateStart, dateFinish, userId));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void merge(String projectName, String description, String dateStart, String dateFinish, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return;
        Project project = projectRepository.findOne(projectName);
        if ((!userId.equals(project.getUserId())) || (!userRepository.findOne(userId).getRole().equals("admin")))
            return;
        if (project != null) {
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        try {
            projectRepository.merge(projectName, description, dateStart, dateFinish);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void remove(String projectName, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return;
        Project project = projectRepository.findOne(projectName);
        if ((!userId.equals(project.getUserId())) || (!userRepository.findOne(userId).getRole().equals("admin")))
            return;
        if (project != null) {
            String projectId = project.getId();
            projectRepository.remove(projectName);
            taskRepository.removeWholeProject(projectId);
        }
    }

    public void removeAll(String userId) {
        if (!userRepository.findOne(userId).getRole().equals("admin")) return;
        projectRepository.removeAll();
        taskRepository.removeAll();
    }

    public List<Project> findAll(String userId) {
        if (!userRepository.findOne(userId).getRole().equals("admin")) return null;
        return projectRepository.findAll();
    }

    public Project findOne(String projectName, String userId) {
        if (projectName.isEmpty() || (projectName == null)) return null;
        return projectRepository.findOne(projectName);
    }


}
