package ru.anikanov.tm.repository;

import ru.anikanov.tm.App;
import ru.anikanov.tm.entity.Project;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectRepository {
    private Map<String, Project> projectMap = new LinkedHashMap<>();

    public Project persist(String projectName, String description, String dateStart, String dateFinish, String userId) throws ParseException {
        Project project = new Project(projectName, description, dateStart, dateFinish, userId);
        projectMap.put(project.getId(), project);
        return project;
    }

    public void merge(String projectName, String description, String dateStart, String dateFinish) throws ParseException {
        Project project = projectMap.get(projectName);
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
