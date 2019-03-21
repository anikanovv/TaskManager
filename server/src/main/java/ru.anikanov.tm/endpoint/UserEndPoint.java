package ru.anikanov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IUserEndPoint;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;
@WebService
public class UserEndPoint {
    private ServiceLocator serviceLocator;
    public UserEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }

    public UserEndPoint() {
    }

    @WebMethod
    public boolean logIn(@WebParam @Nullable final String login, @WebParam @Nullable final String password) {
        return serviceLocator.getUserService().logIn(login,password);
    }

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

    @WebMethod
    public User findOneUser(@WebParam @Nullable final String login, @WebParam @NotNull final String userId) {
        return serviceLocator.getUserService().findOne(login,userId);
    }

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
}
