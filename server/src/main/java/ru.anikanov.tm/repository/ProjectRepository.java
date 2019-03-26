package ru.anikanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;

import java.sql.Connection;
import java.util.*;

@NoArgsConstructor
public class ProjectRepository extends AbstractRepository implements IProjectRepository {
    private Map<String, Project> projectMap = new LinkedHashMap<>();
    private Connection connection;

    public ProjectRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }

    @NotNull
    @Override
    public Project persist(@NotNull final Project project) {
        projectMap.put(project.getId(), project);
        return project;
    }

    @Override
    public Project merge(@NotNull final Project project) {
        if (project.getName() == null) return null;
        @Nullable final Project newProject = findOne(project.getName());
        if (newProject == null) return null;
        newProject.setDescription(project.getDescription());
        newProject.setStart(project.getStart());
        newProject.setEnd(project.getEnd());
        return project;
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

    @Nullable
    public List<Project> sortedByStartDate() {
        @Nullable List<Project> projects = findAll();
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStartDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate() {
        @Nullable List<Project> projects = findAll();
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getEndDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus() {
        @Nullable final List<Project> projects = findAll();
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStatus));
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@NotNull final String partOfName) {
        @Nullable final List<Project> projects = findAll();
        @Nullable Project thisproject = null;
        if (projects == null) return null;
        for (Project project : projects) {
            if ((project.getName() != null) && project.getName().contains(partOfName)) thisproject = project;
        }
        return thisproject;
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription) {
        @Nullable final List<Project> projects = findAll();
        @Nullable Project thisproject = null;
        if (projects == null) return null;
        for (Project project : projects) {
            if ((project.getDescription() != null) && project.getDescription().contains(partOfDescription))
                thisproject = project;
        }
        return thisproject;
    }
}
