package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.dto.UserDto;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@WebService
@NoArgsConstructor
public class UserEndPoint {
    @Autowired
    private IUserService userService;
    @Autowired
    private ISessionService sessionService;


    @WebMethod
    public UserDto logIn(@WebParam @Nullable final String login, @WebParam @Nullable final String password) {
        User user = userService.logIn(login, password);
        return new UserDto(user);
    }

    @Nullable
    @WebMethod
    public UserDto createUser(@WebParam @Nullable final String login, @WebParam @Nullable final String firstName, @WebParam @Nullable final String lastName, @WebParam @Nullable final String email,
                              @WebParam @Nullable final String password, @WebParam @Nullable final Role role) {
        User user = userService.persist(login, firstName, lastName, email, password, role);
        return new UserDto(user);
    }

    @WebMethod
    public void removeUser(@WebParam @NotNull final Session session, @WebParam @Nullable final String login) {
        sessionService.validate(session);
        userService.remove(Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeAllUser(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        userService.removeAll();
    }

    @Nullable
    @WebMethod
    public UserDto findOneUser(@WebParam @NotNull final Session session, @WebParam @Nullable final String userId) {
        sessionService.validate(session);
        User user = userService.findOne(Objects.requireNonNull(userId));
        return new UserDto(user);
    }

    @WebMethod
    public UserDto findOneUserByName(@WebParam @NotNull final String login) {
        User user = userService.findByName(login);
        return new UserDto(user);
    }

    @Nullable
    @WebMethod
    public List<UserDto> findAllUser(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        @NotNull final List<UserDto> listDto = new ArrayList<>();
        @Nullable final List<User> list = userService.findAll();
        if (list == null || list.isEmpty()) return null;
        list.forEach(v -> listDto.add(new UserDto(v)));
        return listDto;
    }

    @WebMethod
    public void updateUser(@WebParam @Nullable final String login, @WebParam @Nullable final String firstName, @WebParam @Nullable final String lastName, @WebParam @Nullable final String email,
                           @WebParam @Nullable final String password, @WebParam @Nullable final Role role, @WebParam @Nullable final String id) {
        userService.merge(login, firstName, lastName, email, password, role, id);
    }

    @WebMethod
    public void updateUserPassword(@WebParam @Nullable final String login, @WebParam @Nullable final String oldOne, @WebParam @Nullable final String newOne) {
        userService.updatePassword(login, oldOne, newOne);
    }

    @WebMethod
    public boolean checkadmin(@WebParam @NotNull final Session session) {
        sessionService.validate(session);
        return userService.checkadmin(session);
    }
}
