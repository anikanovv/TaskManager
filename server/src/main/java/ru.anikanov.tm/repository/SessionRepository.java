package ru.anikanov.tm.repository;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class SessionRepository {
    private Map<String, Session> sessionMap = new LinkedHashMap<>();
    private Statement statement;

    public SessionRepository(@Nullable final Connection connection) {
        try {
            if (connection != null)
                statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    public Session create(Session session) {
        @NotNull final String sql = "INSERT into taskmanager.app_session VALUES('" + session.getId() + "','" + session.getSignature() + "','" + session.getTimestamp() +
                "','" + session.getUserId() + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return session;
    }

    @Nullable
    public Session findOne(String sessionId) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_session WHERE id= '" + sessionId + "'";
        try {
            @NotNull final ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                @NotNull final Session session = Objects.requireNonNull(fetch(resultSet));
                return session;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    @SneakyThrows
    private Session fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final Session session = new Session();
        session.setId(row.getString("id"));
        session.setSignature(row.getString("signature"));
        session.setTimestamp(Long.parseLong(row.getString("timestamp")));
        session.setUserId(row.getString("user_id"));
        return session;
    }
}
