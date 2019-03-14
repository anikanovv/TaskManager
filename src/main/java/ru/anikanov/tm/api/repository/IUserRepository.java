package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.User;

import java.util.List;

public interface IUserRepository extends IRepository {

    @NotNull
    User persist(@NotNull User user);

    void merge(@NotNull User user);

    void remove(@NotNull String login);

    void removeAll();

    @Nullable
    User findOne(@NotNull String login);

    @NotNull
    List<User> findAll();

    boolean logIn(@NotNull String login, @NotNull String password);

    boolean logOut();

    boolean updatePassword(@NotNull String login, @NotNull String oldOne, @NotNull String newOne);


}
