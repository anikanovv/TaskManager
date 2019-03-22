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

@NoArgsConstructor
@WebService
public class UserEndPoint {
    private ServiceLocator serviceLocator;

    public UserEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    @WebMethod
    public boolean logIn(@WebParam @Nullable final String login, @WebParam @Nullable final String password) {
        return serviceLocator.getUserService().logIn(login,password);
    }

    @Nullable
    @WebMethod
    public User createUser(@WebParam @Nullable final String login, @WebParam @Nullable final String password, @WebParam @Nullable final Role role) {
        return serviceLocator.getUserService().persist(login,password,role);
    }

    @WebMethod
    public void removeUser(@WebParam @Nullable final String login, @WebParam @NotNull final String userId) {
        serviceLocator.getUserService().remove(login,userId);
    }

    @WebMethod
    public void removeAllUser(@WebParam @NotNull final String userId) {
        serviceLocator.getUserService().removeAll(userId);
    }

    @Nullable
    @WebMethod
    public User findOneUser(@WebParam @Nullable final String login, @WebParam @NotNull final String userId) {
        return serviceLocator.getUserService().findOne(login,userId);
    }

    @WebMethod
    public User findOneUserByName(@WebParam @NotNull final String login) {
        return serviceLocator.getUserService().findByName(login);
    }
    @Nullable
    @WebMethod
    public List<User> findAllUser(@WebParam @NotNull final String userId) {
        return serviceLocator.getUserService().findAll(userId);
    }

    @WebMethod
    public void updateUser(@WebParam @Nullable final String login, @WebParam @Nullable final String password, @WebParam @Nullable final Role role) {
        serviceLocator.getUserService().merge(login,password,role);
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
