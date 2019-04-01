package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.User;

import java.util.List;

public interface IUserRepository {

    @NotNull
    User persist(@NotNull final User user);

    User merge(@NotNull User user);

    void remove(@NotNull final String login);

    void removeAll();

    @Nullable
    User findOne(@NotNull final String login);

    @Nullable
    List<User> findAll();

    void updatePassword(@NotNull final String login, @NotNull final String oldOne, @NotNull final String newOne);

    User findByName(@NotNull final String name);

}
