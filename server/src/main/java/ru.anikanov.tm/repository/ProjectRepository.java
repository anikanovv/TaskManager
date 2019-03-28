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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@NoArgsConstructor
public class ProjectRepository implements IProjectRepository {
    private Map<String, Project> projectMap = new LinkedHashMap<>();
    private Connection connection;
    private Statement statement;

    public ProjectRepository(@Nullable final Connection connection) throws SQLException {
        this.connection = connection;
        statement = Objects.requireNonNull(connection).createStatement();
    }

    @Nullable
    public Project findOne(@Nullable final String projectId, @Nullable final String userId) {
        @NotNull final String sql = "SELECT * FROM taskmanager.app_project WHERE id='" + projectId + "' AND user_id='" + userId + "'";
        @Nullable Project project = null;
        try {
            @Nullable final ResultSet resultSet = statement.executeQuery(sql);
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
        projectMap.put(project.getId(), project);
        @NotNull final String sql = "INSERT into taskmanager.app_project VALUES('" + project.getId() + "','" + project.getStartDate() + "','" + project.getEndDate() +
                "','" + project.getDescription() + "','" + project.getName() + "','" + Objects.requireNonNull(project.getStatus()).toString() + "','" + project.getUserId() + "')";
        try {
            statement.executeUpdate(sql);
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
                "dateBegin = '" + project.getStartDate() + "', " +
                "dateEnd = '" + project.getEndDate() + "', " +
                "description = '" + project.getDescription() + "', " +
                "name = '" + project.getName() + "' , " +
                "status = '" + Objects.requireNonNull(project.getStatus()).toString() + "'  " +
                "WHERE id = '" + newProject.getId() + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newProject;
    }

    public void remove(@NotNull final String projectName, @NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_project \n" +
                "WHERE name='" + projectName + "' AND user_id='" + userId + "';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeAll(@NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_project \n" +
                "WHERE user_id='" + userId + "';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_project WHERE user_id='" + userId + "'";
        @Nullable final ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
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
                "SELECT * FROM taskmanager.app_project WHERE name LIKE '%" + partOfName + "%'";
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            @Nullable final Project project = fetch(resultSet);
            return project;
        }
        return null;
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) throws SQLException {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_project WHERE description LIKE '%" + partOfDescription + "%'";
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            @Nullable final Project project = fetch(resultSet);
            return project;
        }
        return null;
    }
}
