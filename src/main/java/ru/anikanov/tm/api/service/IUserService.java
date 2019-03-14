package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.List;

public interface IUserService {

    @Nullable
    User persist(@Nullable final String login, @Nullable final String password, @Nullable final Role role);

    void merge(@Nullable final String login, @Nullable final String password, @Nullable final Role role);

    void remove(@Nullable final String login, @Nullable final String userId);

    void removeAll(@Nullable final String userId);

    @Nullable
    User findOne(@Nullable final String login, @NotNull final String userId);

    @Nullable
    List<User> findAll(@NotNull final String userId);

    boolean logIn(@Nullable final String login, @Nullable final String password);

    boolean logOut();

    boolean updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne);


}
