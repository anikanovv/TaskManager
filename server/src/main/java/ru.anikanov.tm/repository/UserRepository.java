package ru.anikanov.tm.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.utils.PasswordHashUtil;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@NoArgsConstructor
public class UserRepository extends AbstractRepository implements IUserRepository {
    @Getter
    private Map<String, User> userMap = new LinkedHashMap<>();
    private Connection connection;
    private EntityManager entityManager;

    public UserRepository(@Nullable final Connection connection, @Nullable final EntityManager entityManager) {
        this.connection = connection;
        this.entityManager = entityManager;
    }

    @NotNull
    public User persist(@NotNull final User user) {
        userMap.put(user.getId(), user);
        @NotNull final String query = "INSERT INTO taskmanager.app_user VALUES (?, ?, ?,?, ?,?, ?);";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getId());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setString(5, user.getName());
            statement.setString(6, user.getHashPassword());
            statement.setString(7, user.getRole().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User merge(@NotNull final User u) {
        @Nullable final User user = findOne((Objects.requireNonNull(u.getName())));
        if (user == null) return null;
        @NotNull final String query = "UPDATE taskmanager.app_user SET " +
                "firstName = ?, " +
                "lastName = ?, " +
                "email = ?, " +
                "role = ?  " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, u.getFirstName());
            statement.setString(2, u.getLastName());
            statement.setString(3, u.getEmail());
            statement.setString(4, u.getRole().toString());
            statement.setString(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updatePassword(@NotNull final String login, @NotNull final String oldOne, @NotNull final String newOne) {
        @Nullable final User user = findOne(login);
        if (user == null) return;
        if (Objects.equals(user.getHashPassword(), PasswordHashUtil.md5(oldOne))) {
            @NotNull final String newPasswordHash = PasswordHashUtil.md5(newOne);
            @NotNull final String query = "UPDATE taskmanager.app_user SET passwordhash=? WHERE login=?;";
            try {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, newPasswordHash);
                statement.setString(2, login);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Nullable
    public User findOne(@NotNull final String id) {
        return entityManager.find(User.class, id);
    }
 /*       @NotNull final String query =
                "SELECT * FROM taskmanager.app_user WHERE login = ?";
        @NotNull final PreparedStatement statement;
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, login);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                @NotNull final User user = Objects.requireNonNull(fetch(resultSet));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/

    @Nullable
    @SneakyThrows
    private User fetch(@Nullable final ResultSet row) {
        if (row == null) return null;
        @NotNull final User user = new User();
        user.setId(row.getString("id"));
        user.setEmail(row.getString("email"));
        user.setFirstName(row.getString("firstName"));
        user.setLastName(row.getString("lastName"));
        user.setName(row.getString("login"));
        user.setHashPassword(row.getString("passwordHash"));
        user.setRole(Role.valueOf(row.getString("role")));
        return user;
    }

    @Nullable
    public List<User> findAll() {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_user";
        @NotNull final PreparedStatement statement;
        try {

            statement = Objects.requireNonNull(connection).prepareStatement(query);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            @NotNull final List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = fetch(resultSet);
                System.out.println(user.getName());
                result.add(user);
            }
            statement.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void remove(@NotNull final String login) {
        @NotNull final String query = "DELETE FROM taskmanager.app_user \n" +
                "WHERE login=?;";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAll() {
        @NotNull final String query = "DELETE FROM taskmanager.app_user ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User findByName(@NotNull final String login) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_user WHERE id = ?";
        PreparedStatement statement;
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, login);
            @NotNull final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                @NotNull final User user = Objects.requireNonNull(fetch(resultSet));
                statement.close();
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
