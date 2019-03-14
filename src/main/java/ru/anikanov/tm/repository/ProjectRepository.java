package ru.anikanov.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ProjectRepository extends AbstractRepository implements IProjectRepository {
    private Map<String, Project> projectMap = new LinkedHashMap<>();

    @NotNull
    @Override
    public Project persist(@NotNull final Project project) {
        projectMap.put(project.getId(), project);
        return project;
    }

    @Override
    public void merge(@NotNull final Project p) throws Exception {
        @Nullable Project project = findOne(p.getName());
        project.setDescription(p.getDescription());
        project.setStart(p.getStart());
        project.setEnd(p.getEnd());
    }

    public void remove(@NotNull final String projectName) {
        projectMap.remove(projectName);
    }

    public void removeAll() {
        projectMap.clear();
    }

    @Nullable
    public List<Project> findAll() {
        @Nullable List<Project> list = new ArrayList<>();
        projectMap.forEach((k, v) -> list.add(v));
        return list;
    }

    @Nullable
    public Project findOne(@NotNull String projectName) {
        return projectMap.get(projectName);
    }


}
