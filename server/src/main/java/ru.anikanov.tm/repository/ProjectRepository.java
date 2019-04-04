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
    @NotNull
    private EntityManager entityManager;

    public ProjectRepository(@NotNull final EntityManager entityManager) {
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
        entityManager.createQuery("DELETE FROM Project project WHERE project.userId = ?1")
                .setParameter(1, userId)
                .executeUpdate();
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.id = ?1 AND project.userId = ?2")
                .setParameter(1, projectId)
                .setParameter(2, userId)
                .getSingleResult();
    }

    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        return entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = ?1")
                .setParameter(1, userId)
                .getResultList();
    }

    @Nullable
    public Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = ?1 AND project.name LIKE ?2")
                .setParameter(1, userId)
                .setParameter(2, "%" + partOfName + "%")
                .getSingleResult();
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = ?1 AND project.description LIKE ?2")
                .setParameter(1, userId)
                .setParameter(2, "%" + partOfDescription + "%")
                .getSingleResult();
    }
}
