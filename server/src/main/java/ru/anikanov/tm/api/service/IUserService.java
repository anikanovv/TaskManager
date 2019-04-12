package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import java.util.List;

public interface IUserService {

    @Nullable
    User persist(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName,
                 @Nullable final String email, @Nullable final String password, @Nullable final Role role);

    void merge(@Nullable final String login, @Nullable final String firstName, @Nullable final String lastName, @Nullable final String email,
               @Nullable final String password, @Nullable final Role role, @NotNull final String id);

    void remove(@NotNull final String userId);

    void removeAll();

    @Nullable
    User findOne(@NotNull final String userId);

    @Nullable
    List<User> findAll();

    User logIn(@Nullable final String login, @Nullable final String password);

    void updatePassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne);

    boolean checkadmin(@NotNull final Session session);

    User findByName(@NotNull final String name);

    User getCurrentUser(@NotNull final Session session);


}
