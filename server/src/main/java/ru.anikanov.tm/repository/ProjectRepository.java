package ru.anikanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateFormatUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {
    private Connection connection;

    public ProjectRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        @NotNull final String sql = "SELECT * FROM taskmanager.app_project WHERE id=? AND user_id=?";
        @Nullable Project project = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projectId);
            statement.setString(2, userId);
            @Nullable final ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            if (resultSet.next()) {
                project = fetch(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
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
    public Project merge(@NotNull final Project project) throws SQLException {
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
    public List<Project> findAll(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_project WHERE user_id=?";
        @Nullable final ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            @NotNull final List<Project> result = new ArrayList<>();
            while (resultSet.next()) {
                @Nullable final Project project = fetch(resultSet);
                if (project == null) break;
                System.out.println(project.getName());
                result.add(project);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    @SneakyThrows
    private Project fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Project project = new Project();
        project.setId(row.getString("id"));
        project.setStartDate(DateFormatUtil.sqlStringToDate(row.getString("dateBegin")));
        project.setEndDate(DateFormatUtil.sqlStringToDate(row.getString("dateEnd")));
        project.setName(row.getString("name"));
        project.setDescription(row.getString("description"));
        project.setStatus(Status.valueOf(row.getString("status")));
        project.setUserId(row.getString("user_id"));
        return project;
    }

    @Nullable
    public List<Project> sortedByStartDate(@NotNull final String userId) {
        @Nullable List<Project> projects = findAll(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStartDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByFinishDate(@NotNull final String userId) {
        @Nullable List<Project> projects = findAll(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getEndDate));
        return projects;
    }

    @Nullable
    public List<Project> sortedByStatus(@NotNull final String userId) {
        @Nullable final List<Project> projects = findAll(userId);
        if (projects == null) return null;
        projects.sort(Comparator.comparing(Project::getStatus));
        return projects;
    }

    @Nullable
    public Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_project WHERE user_id=? AND name LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, "%" + partOfName + "%");
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            @Nullable final Project project = fetch(resultSet);
            return project;
        }
        return null;
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_project WHERE user_id=? AND description LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, userId);
        statement.setString(2, "%" + partOfDescription + "%");
        @NotNull final ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            @Nullable final Project project = fetch(resultSet);
            return project;
        }
        return null;
    }
}
