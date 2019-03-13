package ru.anikanov.tm.repository;

import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Project;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProjectRepository extends AbstractRepository implements IProjectRepository {

    private Map<String, Project> projectMap = new LinkedHashMap<>();

    @Override
    public AbstractEntity persist(AbstractEntity abstractEntity) {
        Project project = (Project) abstractEntity;
        projectMap.put(project.getId(), project);
        return project;
    }

    @Override
    public void merge(AbstractEntity abstractEntity) throws ParseException {
        Project p = (Project) abstractEntity;
        Project project = (Project) findOne(p.getName());
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

    public List<AbstractEntity> findAll() {
        List<AbstractEntity> list = new ArrayList<>();
        projectMap.forEach((k, v) -> list.add(v));
        return list;
    }

    public AbstractEntity findOne(String projectName) {
        AbstractEntity abstractEntity = projectMap.get(projectName);
        return abstractEntity;
    }


}
