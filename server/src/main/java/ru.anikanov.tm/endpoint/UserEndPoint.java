package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@WebService
public class UserEndPoint {
    private ServiceLocator serviceLocator;

    public UserEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    @WebMethod
    public User logIn(@WebParam @Nullable final String login, @WebParam @Nullable final String password) {
        return serviceLocator.getUserService().logIn(login,password);
    }

    @Nullable
    @WebMethod
    public User createUser(@WebParam @Nullable final String login, @WebParam @Nullable final String firstName, @WebParam @Nullable final String lastName, @WebParam @Nullable final String email,
                           @WebParam @Nullable final String password, @WebParam @Nullable final Role role) {
        return serviceLocator.getUserService().persist(login, firstName, lastName, email, password, role);
    }

    @WebMethod
    public void removeUser(@WebParam @NotNull final Session session, @WebParam @Nullable final String login) {
        serviceLocator.getUserService().remove(Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public void removeAllUser(@WebParam @NotNull final Session session) {
        serviceLocator.getUserService().removeAll();
    }

    @Nullable
    @WebMethod
    public User findOneUser(@WebParam @NotNull final Session session, @WebParam @Nullable final String login) {
        return serviceLocator.getUserService().findOne(Objects.requireNonNull(session.getUserId()));
    }

    @WebMethod
    public User findOneUserByName(@WebParam @NotNull final String login) {
        return serviceLocator.getUserService().findByName(login);
    }
    @Nullable
    @WebMethod
    public List<User> findAllUser(@WebParam @NotNull final Session session) {
        return serviceLocator.getUserService().findAll();
    }

    @WebMethod
    public void updateUser(@WebParam @Nullable final String login, @WebParam @Nullable final String firstName, @WebParam @Nullable final String lastName, @WebParam @Nullable final String email,
                           @WebParam @Nullable final String password, @WebParam @Nullable final Role role, @WebParam @Nullable final String id) {
        serviceLocator.getUserService().merge(login, firstName, lastName, email, password, role, id);
    }

    @WebMethod
    public void updateUserPassword(@WebParam @Nullable final String login, @WebParam @Nullable final String oldOne, @WebParam @Nullable final String newOne) {
        serviceLocator.getUserService().updatePassword(login,oldOne,newOne);
    }

    @WebMethod
    public boolean checkadmin(@WebParam @NotNull final Session session) {
        return serviceLocator.getUserService().checkadmin(session);
    }
}
