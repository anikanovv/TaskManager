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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.*;

@NoArgsConstructor
@Getter
public class TaskRepository implements ITaskRepository {


    @NotNull
    private Map<String, Task> taskMap = new LinkedHashMap<>();
    private Connection connection;
    private Statement statement;

    public TaskRepository(@Nullable final Connection connection) {
        this.connection = connection;
        try {
            if (connection != null)
                statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public Task findOne(@NotNull final String taskId, @NotNull final String userId) {
        @NotNull final String sql = "SELECT * FROM taskmanager.app_task WHERE id='" + taskId + "' AND user_id='" + userId + "'";
        @Nullable Task task = null;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
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
        @NotNull final String sql = "INSERT into taskmanager.app_task VALUES('" + entity.getId() + "','" + entity.getStartDate() + "','" + entity.getEndDate() +
                "','" + entity.getTaskDescription() + "','" + entity.getTaskName() + "','" + entity.getStatus().toString() + "','" + entity.getProjectId() + "','" + entity.getUserId() + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Nullable
    public List<Task> findAll(@NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_task WHERE user_id='" + userId + "'";
        @NotNull final ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
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
                "WHERE name='" + taskName + "' AND user_id='" + userId + "';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll(@NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_task \n" +
                "WHERE user_id='" + userId + "';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Task merge(@NotNull final String taskId, @NotNull final String taskName, @NotNull final String description,
                      @NotNull final String dateStart, @NotNull final String dateFinish, @NotNull final String userId) {
        @Nullable final Task task = findOne(taskId, userId);
        if (task == null) return null;
        @Nullable String sql = null;
        try {
            sql = "UPDATE taskmanager.app_task SET " +
                    "dateBegin = '" + new java.sql.Date((new DateFormatUtil().stringToDate(dateStart)).getTime()) + "', " +
                    "dateEnd = '" + new java.sql.Date((new DateFormatUtil().stringToDate(dateFinish)).getTime()) + "', " +
                    "description = '" + description + "', " +
                    "name = '" + taskName + "' " +
                    "WHERE id = '" + taskId + "';";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return task;
    }

    public void removeWholeProject(@NotNull final String projectId, @NotNull final String userId) {
        @NotNull final String sql = "DELETE FROM taskmanager.app_task \n" +
                "WHERE project_id='" + projectId + "' AND user_id='" + userId + "';";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public List<Task> sortedByStartDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStartDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByFinishDate(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getEndDate));
        return tasks;
    }

    @Nullable
    public List<Task> sortedByStatus(@NotNull final String userId) {
        @Nullable List<Task> tasks = findAll(userId);
        if (tasks == null) return null;
        tasks.sort(Comparator.comparing(Task::getStatus));
        return tasks;
    }

    @Nullable
    public Task findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_task WHERE user_id='" + userId + "' AND name LIKE '%" + partOfName + "%'";
        @NotNull final ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
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
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_task WHERE  user_id='" + userId + "' AND description LIKE '%" + partOfDescription + "%'";
        @NotNull final ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(query);
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
