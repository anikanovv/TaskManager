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
import java.text.ParseException;
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

    @NotNull
    @Override
    public Project persist(@NotNull final Project project) {
        projectMap.put(project.getId(), project);
//        java.sql.Date startDate = new java.sql.Date(project.getStartDate().getTime());
//        java.sql.Date endDate = new java.sql.Date(project.getEndDate().getTime());
        String sql = "INSERT into taskmanager.app_project VALUES('" + project.getId() + "','" + project.getStartDate() + "','" + project.getEndDate() +
                "','" + project.getDescription() + "','" + project.getName() + "','" + project.getStatus().toString() + "','" + project.getUserId() + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public Project merge(@NotNull final Project project) {
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

    public void removeAll(@NotNull final String userId) {
        projectMap.forEach((k, v) -> {
            if (userId.equals(v.getUserId())) projectMap.remove(k);
        });
    }

    @Nullable
    public List<Project> findAll(@NotNull final String userId) {
        @Nullable List<Project> list = new ArrayList<>();
        projectMap.forEach((k, v) -> {
            if (userId.equals(v.getUserId())) list.add(v);
        });
        return list;
    }

    @Nullable
    public Project findOne(@Nullable final String projectId) {
        String sql = "SELECT * FROM taskmanager.app_project WHERE id='" + projectId + "'";
        Project project = null;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                project = fetch(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Nullable
    @SneakyThrows
    public Project fetch(@Nullable final ResultSet row) {
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
    public Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) throws SQLException, ParseException {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_project WHERE name LIKE '%" + partOfName + "%'";
//        @NotNull final PreparedStatement statement =
//                Objects.requireNonNull(connection).prepareStatement(query);
//        statement.setString(1, userId);
//        statement.setString(1, partOfName);
        @NotNull final ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            @NotNull final Project project = fetch(resultSet);
            statement.close();
            return project;
        }
        return null;
    }

    @Nullable
    public Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        @Nullable final List<Project> projects = findAll(userId);
        @Nullable Project thisproject = null;
        if (projects == null) return null;
        for (Project project : projects) {
            if ((project.getDescription() != null) && project.getDescription().contains(partOfDescription))
                thisproject = project;
        }
        return thisproject;
    }


/*

    @NoArgsConstructor
    public final class ProjectRepository extends AbstractRepository<Project> implements IProjectRepository {

        @Nullable
        private Connection connection;

        public ProjectRepository(@Nullable final Connection connection) {
            this.connection = connection;
        }

        @NotNull
        @Override
        public Project persist(@NotNull final String userId, @NotNull final Date dateBegin,
                               @NotNull final Date dateEnd, @NotNull final String description,
                               @NotNull final String name) throws IOException, NoSuchAlgorithmException, SQLException {
            @NotNull final Project project = new Project();
            project.setUserId(userId);
            project.setDateBegin(dateBegin);
            project.setDateEnd(dateEnd);
            project.setName(name);
            project.setDescription(description);
            @NotNull final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @NotNull final String query = "INSERT INTO tm.app_project (id, dateBegin, dateEnd, description, name, user_id) \n" +
                    " VALUES ('" + project.getId() + "', '" + sdf.format(dateBegin) + "', '"
                    + sdf.format(dateEnd) + "', '" + description + "', '" + name + "', '" + userId + "');";
            @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.executeUpdate(query);

            return project;
        }

        @Override
        public void merge(@NotNull final Project project) throws SQLException {
            @NotNull final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @NotNull final String query = "UPDATE tm.app_project SET " +
                    "name = '" + project.getName() + "', " +
                    "description = '" + project.getDescription() + "', " +
                    "dateBegin = '" + sdf.format(project.getDateBegin()) + "', " +
                    "dateEnd = '" + sdf.format(project.getDateEnd()) + "'  " +
                    "WHERE id = '" + project.getId() + "'";
            @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.executeUpdate(query);
        }

        @Override
        public void remove(@NotNull final String id, @NotNull final String userId) throws SQLException {
            @NotNull final String query = "DELETE FROM tm.app_project " +
                    "WHERE id = '" + id + "' AND user_id = '" + userId + "'";
            @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.executeUpdate(query);
        }

        @Override
        public void removeAll(@NotNull final String userId) throws SQLException {
            @NotNull final String query = "DELETE FROM tm.app_project " +
                    "WHERE user_id = '" + userId + "'";
            @NotNull final Statement statement = Objects.requireNonNull(connection).createStatement();
            statement.executeUpdate(query);
        }

        @NotNull
        @Override
        public List<Project> findAll(@NotNull final String userId) throws SQLException {
            @NotNull final String query =
                    "SELECT * FROM tm.app_project WHERE user_id = ?";
            @NotNull final PreparedStatement statement =
                    Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, userId);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            @NotNull final List<Project> result = new ArrayList<>();
            while (resultSet.next()) result.add(fetch(resultSet));
            statement.close();
            return result;
        }

        @Nullable
        @Override
        public Project findOne(@NotNull final String id, @NotNull final String userId) throws SQLException {
            @NotNull final String query =
                    "SELECT * FROM tm.app_project WHERE id = ? AND user_id = ?";
            @NotNull final PreparedStatement statement =
                    Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, userId);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
                statement.close();
                return project;
            }
            return null;
        }

        @Nullable
        @SneakyThrows
        private Project fetch(@Nullable final ResultSet row) {
            if (row == null) return null;
            @NotNull final Project project = new Project();
            project.setId(row.getString(FieldConst.ID));
            project.setName(row.getString(FieldConst.NAME));
            project.setDescription(row.getString(FieldConst.DESCRIPTION));
            project.setDateBegin(row.getDate(FieldConst.DATE_BEGIN));
            project.setDateEnd(row.getDate(FieldConst.DATE_END));
            project.setUserId(row.getString(FieldConst.USER_ID));
            return project;
        }


        @NotNull
        @Override
        public List<Project> findAllSortByDateBegin(@NotNull final String userId) throws SQLException {
            @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
            result.sort((s1, s2) -> {
                @NotNull final boolean firstDateMoreThanSecond = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() < 0;
                @NotNull final boolean secondDateMareFirst = s1.getDateBegin().getTime() - s2.getDateBegin().getTime() > 0;

                if (firstDateMoreThanSecond) {
                    return 1;
                } else if (secondDateMareFirst) {
                    return -1;
                } else {
                    return 0;
                }
            });
            return result;
        }

        @NotNull
        @Override
        public List<Project> findAllSortByDateEnd(@NotNull final String userId) throws SQLException {
            @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
            result.sort((s1, s2) -> {
                boolean firstDateMoreThanSecond = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() > 0;
                boolean secondDateMoreThanFirst = Objects.requireNonNull(s1.getDateEnd()).getTime() - Objects.requireNonNull(s2.getDateEnd()).getTime() < 0;

                if (firstDateMoreThanSecond) {
                    return 1;
                } else if (secondDateMoreThanFirst) {
                    return -1;
                } else {
                    return 0;
                }
            });
            return result;
        }

        @NotNull
        @Override
        public List<Project> findAllSortByStatus(@NotNull final String userId) throws SQLException {
            @NotNull final List<Project> result = Objects.requireNonNull(findAll(userId));
            result.sort((s1, s2) -> Integer.compare(0, s1.getStatus().ordinal() - s2.getStatus().ordinal()));
            return result;
        }

        @Nullable
        @Override
        public Project findOneByName(@NotNull final String userId, @NotNull final String name) throws SQLException {
            @NotNull final String query =
                    "SELECT * FROM tm.app_project WHERE user_id = ? AND name = ?";
            @NotNull final PreparedStatement statement =
                    Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, name);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
                statement.close();
                return project;
            }
            return null;
        }

        @Nullable
        @Override
        public Project findOneByDescription(@NotNull final String userId, @NotNull final String description) throws SQLException {
            @NotNull final String query =
                    "SELECT * FROM tm.app_project WHERE user_id = ? AND description = ?";
            @NotNull final PreparedStatement statement =
                    Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, userId);
            statement.setString(2, description);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                @NotNull final Project project = Objects.requireNonNull(fetch(resultSet));
                statement.close();
                return project;
            }
            return null;
        }
    }*/


}
