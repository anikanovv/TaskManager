package ru.anikanov.tm.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IUserEndPoint;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.enumeration.Role;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;
@WebService(endpointInterface = "ru.anikanov.tm.api.endpoint.IUserEndPoint")
public class UserEndPoint implements IUserEndPoint {
    private ServiceLocator serviceLocator;
    UserEndPoint(){

    }
    UserEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }
    public boolean logIn(@Nullable final String login, @Nullable final String password){
        return serviceLocator.getUserService().logIn(login,password);
    }
    public User createUser(@Nullable final String login, @Nullable final String password, @Nullable final Role role){
        return serviceLocator.getUserService().persist(login,password,role);
    }
    public void removeUser(@Nullable final String login, @NotNull final String userId){
        serviceLocator.getUserService().remove(login,userId);
    }
    public void removeAllUser(@NotNull final String userId){
        serviceLocator.getUserService().removeAll(userId);
    }
    public User findOneUser(@Nullable final String login, @NotNull final String userId){
        return serviceLocator.getUserService().findOne(login,userId);
    }
    public List<User>findAllUser(@NotNull final String userId){
        return serviceLocator.getUserService().findAll(userId);
    }
    public void updateUser(@Nullable final String login, @Nullable final String password, @Nullable final Role role){
        serviceLocator.getUserService().merge(login,password,role);
    }
    public void updateUserPassword(@Nullable final String login, @Nullable final String oldOne, @Nullable final String newOne){
        serviceLocator.getUserService().updatePassword(login,oldOne,newOne);
    }
    public static void main(String[] args){
        Endpoint.publish("http://localhost:8080/UserEndpoint?wsdl",new UserEndPoint());
    }
}
