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

import java.sql.*;
import java.util.*;

@NoArgsConstructor
public class UserRepository extends AbstractRepository implements IUserRepository {
    @Getter
    private Map<String, User> userMap = new LinkedHashMap<>();
    private Connection connection;
    private Statement statement;

    public UserRepository(Connection connection) throws SQLException {
        this.connection = connection;
        statement = connection.createStatement();
    }

    @NotNull
    public User persist(@NotNull final User user) {
        userMap.put(user.getId(), user);
        String sql = "INSERT INTO taskmanager.app_user VALUES ('" + user.getId() + "', '" + user.getEmail() + "', '" + user.getFirstName() + "','" + user.getLastName() + "', '" + user.getName() +
                "','" + user.getHashPassword() + "', '" + (user.getRole()).toString() + "');";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User merge(@NotNull final User u) {
        @Nullable final User user = findOne(Objects.requireNonNull(u.getName()));
        String sql = "UPDATE taskmanager.app_user SET " +
                "firstName = '" + u.getFirstName() + "', " +
                "lastName = '" + u.getLastName() + "', " +
                "email = '" + u.getEmail() + "', " +
                "role = '" + u.getRole().toString() + "'  " +
                "WHERE id = '" + user.getId() + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean logIn(@NotNull final String login, @NotNull final String password) {
        @Nullable final User user = findOne(login);
        if (user == null) return false;
        return Objects.equals(user.getHashPassword(), PasswordHashUtil.md5(password));
    }

    public boolean logOut() {
        return true;
    }

    public boolean updatePassword(@NotNull final String login, @NotNull final String oldOne, @NotNull final String newOne) {
        @Nullable User user = findOne(login);
        if (user == null) return false;
        if (Objects.equals(user.getHashPassword(), PasswordHashUtil.md5(oldOne))) {
            String newPasswordHash = PasswordHashUtil.md5(newOne);
            String sql = "UPDATE taskmanager.app_user SET passwordhash='" + newPasswordHash + "'";
            try {
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return true;
        } else return false;

    }

    @Nullable
    public User findOne(@NotNull final String login) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_user WHERE login = ?";
        @NotNull final PreparedStatement statement;
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, login);
//        statement.setString(2, userId);
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
        String sql = "DELETE FROM taskmanager.app_user \n" +
                "WHERE login='" + login + "';";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        userMap.remove(login);
    }

    public void removeAll() {
        String sql = "DELETE FROM taskmanager.app_user ";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        userMap.remove(login);
    }

    public User findByName(@NotNull final String login) {
        @NotNull final String query =
                "SELECT * FROM taskmanager.app_user WHERE id = ?";
        @NotNull final PreparedStatement statement;
        try {
            statement = Objects.requireNonNull(connection).prepareStatement(query);
            statement.setString(1, login);
//        statement.setString(2, userId);
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
