package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.repository.ProjectRepository;
import ru.anikanov.tm.repository.TaskRepository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository;
    private TaskRepository taskRepository;

    public ProjectService(ProjectRepository pr, TaskRepository tr) {
        projectRepository = pr;
        taskRepository = tr;
    }

    public Project persist(String projectName, String description, String dateStart, String dateFinish) throws ParseException {
        if (projectName.isEmpty() || (projectName == null)) return null;
        Project project = projectRepository.findOne(projectName);
        if (project == null) {
            if (description.isEmpty() || (description == null)) return null;
            if (dateStart.isEmpty() || (dateStart == null)) return null;
            if (dateFinish.isEmpty() || (dateFinish == null)) return null;
            return (projectRepository.persist(projectName, description, dateStart, dateFinish));
        }
        return null;
    }

    public void merge(String projectName, String description, String dateStart, String dateFinish) throws ParseException {
        if (projectName.isEmpty() || (projectName == null)) return;
        Project project = projectRepository.findOne(projectName);
        if (project != null) {
            if (description.isEmpty() || (description == null)) return;
            if (dateStart.isEmpty() || (dateStart == null)) return;
            if (dateFinish.isEmpty() || (dateFinish == null)) return;
        }
        projectRepository.merge(projectName, description, dateStart, dateFinish);
    }

    public void remove(String projectName) {
        if (projectName.isEmpty() || (projectName == null)) return;
        Project project = projectRepository.findOne(projectName);
        if (project != null) {
            String projectId = project.getId();
            projectRepository.remove(projectName);
            taskRepository.removeWholeProject(projectId);
        }
    }

    public void removeAll() {
        projectRepository.removeAll();
        taskRepository.removeAll();
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findOne(String projectName) {
        if (projectName.isEmpty() || (projectName == null)) return null;
        return projectRepository.findOne(projectName);
    }


}
