package ru.anikanov.tm.repository;

import lombok.SneakyThrows;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.session.SqlSession;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.utils.MyBatisUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SessionRepository {
    private Connection connection;

    public SessionRepository(@Nullable final Connection connection) {
        this.connection = connection;
    }


    @Insert("INSERT into taskmanager.app_session (id,signature,timestamp,user_id) VALUES(#{id}, #{signature}, #{timestamp}, #{userId})")
    public Session create(Session session) {
        try (SqlSession sqlSession = MyBatisUtil.getSessionFactory().openSession()) {
            SessionRepository sessionMapper = sqlSession.getMapper(SessionRepository.class);
            sessionMapper.create(session);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
      /*  @NotNull final String sql = "INSERT into taskmanager.app_session VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, session.getId());
            statement.setString(2, session.getSignature());
            statement.setLong(3, session.getTimestamp());
            statement.setString(4, session.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return session;
    }

    @Nullable
    public Session findOne(String sessionId) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_session WHERE id= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, sessionId);
            @NotNull final ResultSet resultSet = statement.executeQuery();
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
