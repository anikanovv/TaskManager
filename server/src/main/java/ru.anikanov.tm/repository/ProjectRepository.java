package ru.anikanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.util.List;

@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {
    private EntityManager entityManager;

    public ProjectRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @NotNull
    @Override
    public Project persist(@NotNull final Project project) {
        entityManager.persist(project);
        return project;
    }

    @Override
    public Project merge(@NotNull final Project project) {
        return entityManager.merge(project);
    }

    public void remove(@NotNull final Project project) {
        entityManager.remove(project);
    }

    public void removeAll(@NotNull final String userId) {
        entityManager.createQuery("DELETE FROM Project project WHERE project.userId = :userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.id = :id AND project.userId = :userId")
                .setParameter("id", projectId)
                .setParameter("userId", userId)
                .getSingleResult();
    }

    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        return entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
    }

    @Nullable
    public Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = :userId AND project.name LIKE :name")
                .setParameter("userId", userId)
                .setParameter("name", "%" + partOfName + "%")
                .getSingleResult();
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = :userId AND project.description LIKE :description")
                .setParameter("userId", userId)
                .setParameter("description", "%" + partOfDescription + "%")
                .getSingleResult();
    }
}
