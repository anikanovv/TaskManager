package ru.anikanov.tm.repository;

import ru.anikanov.tm.App;
import ru.anikanov.tm.entity.Project;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProjectRepository {
    App app = new App();
    private Map<String, Project> projectMap = app.getReferencesProjects();

    public Project persist(String projectName, String description, String dateStart, String dateFinish) throws ParseException {
        Project project = new Project(projectName, description, dateStart, dateFinish);
        if (!projectMap.containsValue(project)) {
            projectMap.put(project.getId(), project);
            return project;
        }
        return null;
    }

    public void merge(String projectId, String projectName, String description, String dateStart, String dateFinish) throws ParseException {
        Project project = projectMap.get(projectName);
        project.setId(projectId);
        project.setName(projectName);
        project.setDescription(description);
        project.setStart(dateStart);
        project.setEnd(dateFinish);
    }

    public void remove(String projectName) {
        projectMap.remove(projectName);
    }

    public void removeAll() {
        projectMap.clear();
    }

    public List<Project> findAll() {
        List<Project> list = new ArrayList<>();
        projectMap.forEach((k, v) -> list.add(v));
        return list;
    }

    public Project findOne(String projectName) {
        Project project = projectMap.get(projectName);
        return project;
    }


}
