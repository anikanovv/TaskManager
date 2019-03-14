package ru.anikanov.tm.repository;

import lombok.Getter;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectRepository extends AbstractRepository implements IProjectRepository {
    @Getter
    private Map<String, Project> projectMap = new LinkedHashMap<>();

    @Override
    public Project persist(Project project) {
        projectMap.put(project.getId(), project);
        return project;
    }

    @Override
    public void merge(Project p) throws Exception {
        Project project = findOne(p.getName());
        project.setDescription(p.getDescription());
        project.setStart(p.getStart());
        project.setEnd(p.getEnd());
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
