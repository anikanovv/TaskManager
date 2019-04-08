package ru.anikanov.tm.service;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.repository.ProjectRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

@ApplicationScoped
public class ProjectService implements IProjectService {
    @Inject
    private EntityManager entityManager;

    @Nullable
    public Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                           @Nullable final String dateFinish, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return null;
        if (userId == null || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            if ((description == null) || description.isEmpty()) return null;
            if ((dateStart == null) || dateStart.isEmpty()) return null;
            if ((dateFinish == null) || dateFinish.isEmpty()) return null;
            @Nullable final Project newproject = new Project(projectName, description, dateStart, dateFinish, userId);
            entityManager.getTransaction().begin();
            projectRepository.persist(newproject);
            entityManager.getTransaction().commit();
            return newproject;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    public void merge(@Nullable final String id, @Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart,
                      @Nullable final String dateFinish, @Nullable final String userId) {
        if ((projectName == null) || projectName.isEmpty()) return;
        if ((description == null) || description.isEmpty()) return;
        if ((dateStart == null) || dateStart.isEmpty()) return;
        if ((dateFinish == null) || dateFinish.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            @Nullable Project p = findOne(id, userId);
            if (p == null) p = new Project();
            p.setName(projectName);
            p.setDescription(description);
            p.setStart(dateStart);
            p.setEnd(dateFinish);
            p.setUserId(userId);
            entityManager.getTransaction().begin();
            projectRepository.merge(p);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void remove(@Nullable final String id, @Nullable final String userId) {
        if ((id == null) || id.isEmpty()) return;
        if ((userId == null) || userId.isEmpty()) return;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            @Nullable final Project project = projectRepository.findOne(id, userId);
            if (project == null) return;
            entityManager.getTransaction().begin();
            projectRepository.remove(project);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    public void removeAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        try {
            entityManager.getTransaction().begin();
            projectRepository.removeAll(userId);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        if ((projectId == null) || projectId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findOne(projectId, userId);
    }

    @Nullable
    public List<Project> findAll(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findAll(userId);
    }

    @Nullable
    public List<Project> sortedByStartDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        @Nullable List<Project> projects = projectRepository.findAll(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStartDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        @Nullable List<Project> projects = projectRepository.findAll(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getEndDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus(@Nullable final String userId) {
        if ((userId == null) || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        @Nullable List<Project> projects = projectRepository.findAll(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStatus));
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@Nullable final String partOfName, @Nullable final String userId) {
        if ((partOfName == null) || (partOfName.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findByPartOfName(partOfName, userId);
    }

    @Nullable
    public Project findByPartOfDescription(@Nullable final String partOfDescription, @Nullable final String userId) {
        if ((partOfDescription == null) || (partOfDescription.isEmpty())) return null;
        if ((userId == null) || userId.isEmpty()) return null;
        final IProjectRepository projectRepository = new ProjectRepository(entityManager);
        return projectRepository.findByPartOfDescription(partOfDescription, userId);
    }

}
