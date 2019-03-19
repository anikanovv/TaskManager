package ru.anikanov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@WebService
public interface IUserEndPoint {
    @WebMethod
    boolean logIn(@Nullable final String login, @Nullable final String password);

    @WebMethod User createUser(@Nullable final String login, @Nullable final String password, @Nullable final Role role);

    @WebMethod void removeUser(@Nullable final String login, @NotNull final String userId);

    @WebMethod void removeAllUser(@NotNull final String userId);

    @WebMethod User findOneUser(@Nullable final String login, @NotNull final String userId);

    @WebMethod List<User> findAllUser(@NotNull final String userId);

    @WebMethod void updateUser(@Nullable final String login, @Nullable final String password, @Nullable final Role role);

    @WebMethod void updateUserPassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne);
}
