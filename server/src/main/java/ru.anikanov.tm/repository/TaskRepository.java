package ru.anikanov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateFormatUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class TaskRepository implements ITaskRepository {

    @NotNull
    private Map<String, Task> taskMap = new LinkedHashMap<>();
    private Connection connection;

    public TaskRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }
    @Nullable
    public Task findOne(@NotNull final String taskId, @NotNull final String userId) {
        @NotNull final String sql = "SELECT * FROM taskmanager.app_task WHERE id=? AND user_id=?";
        @Nullable Task task = null;
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, taskId);
            statement.setString(2, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                task = fetch(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    @Nullable
    @SneakyThrows
    private Task fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Task task = new Task();
        task.setId(row.getString("id"));
        task.setStartDate(DateFormatUtil.sqlStringToDate(row.getString("dateBegin")));
        task.setEndDate(DateFormatUtil.sqlStringToDate(row.getString("dateEnd")));
        task.setTaskName(row.getString("name"));
        task.setTaskDescription(row.getString("description"));
        task.setStatus(Status.valueOf(row.getString("status")));
        task.setProjectId(row.getString("project_id"));
        task.setUserId(row.getString("user_id"));
        return task;
    }

    @Nullable
    public Task persist(@NotNull final Task entity) {
        @NotNull final String sql = "INSERT into taskmanager.app_task VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getId());
            statement.setDate(2, new java.sql.Date(entity.getStartDate().getTime()));
            statement.setDate(3, new java.sql.Date(entity.getEndDate().getTime()));
            statement.setString(4, entity.getTaskDescription());
            statement.setString(5, entity.getTaskName());
            statement.setString(6, entity.getStatus().toString());
            statement.setString(7, entity.getProjectId());
            statement.setString(8, entity.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Nullable
    public List<Task> findAll(@NotNull final String userId) {
        @NotNull final String sql =
                "SELECT * FROM taskmanager.app_task WHERE user_id=?";
        @NotNull final ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();
            @NotNull final List<Task> result = new ArrayList<>();
            while (resultSet.next()) {
                @Nullable final Task task = fetch(resultSet);
                if (task == null) break;
                System.out.println(task.getTaskName());
                result.add(task);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void remove(@NotNull final String taskName, @NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_task \n" +
                "WHERE name=? AND user_id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, taskName);
            statement.setString(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll(@NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_task \n" +
                "WHERE user_id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task merge(@NotNull final String taskId, @NotNull final String taskName, @NotNull final String description,
                      @NotNull final String dateStart, @NotNull final String dateFinish, @NotNull final String userId) {
        @Nullable final Task task = findOne(taskId, userId);
        if (task == null) return null;
        @Nullable String sql = "UPDATE taskmanager.app_task SET " +
                "dateBegin = ?, " +
                "dateEnd = ?, " +
                "description = ?, " +
                "name = ? " +
                "WHERE id = ?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date((new DateFormatUtil().stringToDate(dateStart)).getTime()));
            statement.setDate(2, new java.sql.Date((new DateFormatUtil().stringToDate(dateFinish)).getTime()));
            statement.setString(3, description);
            statement.setString(4, taskName);
            statement.setString(5, taskId);
            statement.executeUpdate();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void removeWholeProject(@NotNull final String projectId, @NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_task \n" +
                "WHERE project_id=? AND user_id=?;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, projectId);
            statement.setString(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        @NotNull final String sql =
                "SELECT * FROM taskmanager.app_task WHERE user_id=? AND name LIKE ?";
        @NotNull final ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, "%" + partOfName + "%");
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                @Nullable final Task task = fetch(resultSet);
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public Task findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) {
        @NotNull final String sql =
                "SELECT * FROM taskmanager.app_task WHERE  user_id=? AND description LIKE ?";
        @NotNull final ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.setString(2, "%" + partOfDescription + "%");
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                @Nullable final Task task = fetch(resultSet);
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
