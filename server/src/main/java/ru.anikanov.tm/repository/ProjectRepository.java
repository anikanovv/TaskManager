package ru.anikanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {
    private Connection connection;
    @NotNull
    private EntityManager entityManager;

    public ProjectRepository(@NotNull final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @NotNull
    @Override
    public Project persist(@NotNull final Project project) {
        @NotNull final String sql = "INSERT into taskmanager.app_project VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, project.getId());
            statement.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            statement.setString(4, project.getDescription());
            statement.setString(5, project.getName());
            statement.setString(6, (project.getStatus()).toString());
            statement.setString(7, project.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project merge(@NotNull final Project project) {
        @Nullable final Project newProject = findByPartOfName(Objects.requireNonNull(project.getName()), Objects.requireNonNull(project.getUserId()));
        if (newProject == null) return null;
        @NotNull final String sql = "UPDATE taskmanager.app_project SET " +
                "dateBegin = ?, " +
                "dateEnd = ?, " +
                "description = ?, " +
                "name = ? , " +
                "status = ?  " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(project.getStartDate().getTime()));
            statement.setDate(2, new java.sql.Date(project.getEndDate().getTime()));
            statement.setString(3, project.getDescription());
            statement.setString(4, project.getName());
            statement.setString(5, Objects.requireNonNull(project.getStatus()).toString());
            statement.setString(6, project.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newProject;
    }

    public void remove(@NotNull final String projectName, @NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_project \n" +
                "WHERE name=? AND user_id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projectName);
            statement.setString(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll(@NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_project \n" +
                "WHERE user_id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = ?1 AND project.partOfName LIKE ?2")
                .setParameter(1, userId)
                .setParameter(2, partOfName)
                .getSingleResult();
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        return (Project) entityManager.createQuery("SELECT project FROM Project project WHERE project.userId = ?1 AND project.partOfDescription LIKE ?2")
                .setParameter(1, userId)
                .setParameter(2, partOfDescription)
                .getSingleResult();
    }
}
