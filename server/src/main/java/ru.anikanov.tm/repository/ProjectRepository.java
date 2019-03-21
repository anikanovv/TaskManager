package ru.anikanov.tm.repository;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;

import java.util.*;

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
        if (p.getName() == null) throw new Exception();
        @Nullable final Project project = findOne(p.getName());
        if (project == null) throw new Exception();
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

    @Nullable
    public List<Project> sortedByStartDate() throws Exception {
        @Nullable List<Project> projects = findAll();
        if (projects == null) throw new Exception();
        projects.sort(Comparator.comparing(Project::getStartDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate() throws Exception {
        @Nullable List<Project> projects = findAll();
        if (projects == null) throw new Exception();
        projects.sort(Comparator.comparing(Project::getEndDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus() throws Exception {
        @Nullable final List<Project> projects = findAll();
        if (projects == null) throw new Exception();
        projects.sort(Comparator.comparing(Project::getStatus));
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@NotNull final String partOfName) throws Exception {
        @Nullable final List<Project> projects = findAll();
        @Nullable Project thisproject = null;
        if (projects == null) throw new Exception();
        for (Project project : projects) {
            if ((project.getName() != null) && project.getName().contains(partOfName)) thisproject = project;
        }
        return thisproject;
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription) throws Exception {
        @Nullable final List<Project> projects = findAll();
        @Nullable Project thisproject = null;
        if (projects == null) throw new Exception();
        for (Project project : projects) {
            if ((project.getDescription() != null) && project.getDescription().contains(partOfDescription))
                thisproject = project;
        }
        return thisproject;
    }
}
