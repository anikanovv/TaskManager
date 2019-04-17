package ru.anikanov.tm.service;

import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.repository.ProjectRepository;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class ProjectService implements IProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Nullable
    public Project persist(@Nullable final String userId, @Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                           @Nullable final String dateFinish) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        if (userId == null || userId.isEmpty()) return null;
        if ((description == null) || description.isEmpty()) return null;
        if ((dateStart == null) || dateStart.isEmpty()) return null;
        if ((dateFinish == null) || dateFinish.isEmpty()) return null;
        @Nullable final Project newproject = new Project(projectName, description, dateStart, dateFinish, userId);
        projectRepository.save(newproject);
        return newproject;
    }

    public void merge(@Nullable final String userId, @Nullable final String id, @Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                      @Nullable final String dateFinish) {
        if ((projectName == null) || projectName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable Project p = findOne(id, userId);
        if (p == null) p = new Project();
        p.setName(projectName);
        p.setDescription(description);
        p.setStart(dateStart);
        p.setEnd(dateFinish);
        p.setUserId(userId);
        projectRepository.save(p);
    }

    public void remove(@Nullable final String userId, @Nullable final String id) {
        if ((id == null) || id.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        @Nullable final Project project = projectRepository.findByUserIdAndId(id, userId);
        if (project == null) return;
        projectRepository.delete(project);
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        projectRepository.deleteAllById(userId);
    }

    @Nullable
    public Project findOne(@Nullable final String userId, @Nullable final String projectId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((projectId == null) || projectId.isEmpty()) return null;
        return projectRepository.findByUserIdAndId(userId, projectId);
    }

    @Nullable
    public List<Project> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.findAllByUserId(userId);
    }

    @Nullable
    public List<Project> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Project> projects = projectRepository.findAllByUserId(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStartDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Project> projects = projectRepository.findAllByUserId(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getEndDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        @Nullable List<Project> projects = projectRepository.findAllByUserId(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStatus));
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@Nullable final String userId, @Nullable String partOfName) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.findByUserIdAndName(userId, partOfName);
    }

    @Nullable
    public Project findByPartOfDescription(@Nullable final String userId, @Nullable final String partOfDescription) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        return projectRepository.findByUserIdAndDescription(userId, partOfDescription);
    }

}
