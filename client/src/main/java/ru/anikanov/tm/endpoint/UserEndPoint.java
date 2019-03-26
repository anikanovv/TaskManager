package ru.anikanov.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-26T14:48:39.902+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.anikanov.ru/", name = "UserEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface UserEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/checkadminRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/checkadminResponse")
    @RequestWrapper(localName = "checkadmin", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.Checkadmin")
    @ResponseWrapper(localName = "checkadminResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CheckadminResponse")
    @WebResult(name = "return", targetNamespace = "")
    boolean checkadmin(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/findAllUserRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/findAllUserResponse")
    @RequestWrapper(localName = "findAllUser", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindAllUser")
    @ResponseWrapper(localName = "findAllUserResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindAllUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    java.util.List<ru.anikanov.tm.endpoint.User> findAllUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/updateUserPasswordRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/updateUserPasswordResponse")
    @RequestWrapper(localName = "updateUserPassword", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateUserPassword")
    @ResponseWrapper(localName = "updateUserPasswordResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateUserPasswordResponse")
    void updateUserPassword(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1,
            @WebParam(name = "arg2", targetNamespace = "")
                    java.lang.String arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/removeUserRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/removeUserResponse")
    @RequestWrapper(localName = "removeUser", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveUser")
    @ResponseWrapper(localName = "removeUserResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveUserResponse")
    void removeUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/removeAllUserRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/removeAllUserResponse")
    @RequestWrapper(localName = "removeAllUser", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveAllUser")
    @ResponseWrapper(localName = "removeAllUserResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveAllUserResponse")
    void removeAllUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/createUserRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/createUserResponse")
    @RequestWrapper(localName = "createUser", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateUser")
    @ResponseWrapper(localName = "createUserResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.anikanov.tm.endpoint.User createUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1,
            @WebParam(name = "arg2", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Role arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/updateUserRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/updateUserResponse")
    @RequestWrapper(localName = "updateUser", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateUser")
    @ResponseWrapper(localName = "updateUserResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateUserResponse")
    void updateUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1,
            @WebParam(name = "arg2", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Role arg2
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/findOneUserByNameRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/findOneUserByNameResponse")
    @RequestWrapper(localName = "findOneUserByName", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindOneUserByName")
    @ResponseWrapper(localName = "findOneUserByNameResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindOneUserByNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.anikanov.tm.endpoint.User findOneUserByName(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/logInRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/logInResponse")
    @RequestWrapper(localName = "logIn", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.LogIn")
    @ResponseWrapper(localName = "logInResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.LogInResponse")
    @WebResult(name = "return", targetNamespace = "")
    boolean logIn(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/UserEndPoint/findOneUserRequest", output = "http://endpoint.tm.anikanov.ru/UserEndPoint/findOneUserResponse")
    @RequestWrapper(localName = "findOneUser", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindOneUser")
    @ResponseWrapper(localName = "findOneUserResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindOneUserResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.anikanov.tm.endpoint.User findOneUser(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    java.lang.String arg1
    );
}
