
package ru.anikanov.tm.api.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.anikanov.tm.api.endpoint package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateUser_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "createUser");
    private final static QName _CreateUserResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "createUserResponse");
    private final static QName _FindAllUser_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "findAllUser");
    private final static QName _FindAllUserResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "findAllUserResponse");
    private final static QName _FindOneUser_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "findOneUser");
    private final static QName _FindOneUserResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "findOneUserResponse");
    private final static QName _LogIn_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "logIn");
    private final static QName _LogInResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "logInResponse");
    private final static QName _RemoveAllUser_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "removeAllUser");
    private final static QName _RemoveAllUserResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "removeAllUserResponse");
    private final static QName _RemoveUser_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "removeUser");
    private final static QName _RemoveUserResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "removeUserResponse");
    private final static QName _UpdateUser_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "updateUser");
    private final static QName _UpdateUserPassword_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "updateUserPassword");
    private final static QName _UpdateUserPasswordResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "updateUserPasswordResponse");
    private final static QName _UpdateUserResponse_QNAME = new QName("http://endpoint.api.tm.anikanov.ru/", "updateUserResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.anikanov.tm.api.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
    }

    /**
     * Create an instance of {@link FindAllUser }
     * 
     */
    public FindAllUser createFindAllUser() {
        return new FindAllUser();
    }

    /**
     * Create an instance of {@link FindAllUserResponse }
     * 
     */
    public FindAllUserResponse createFindAllUserResponse() {
        return new FindAllUserResponse();
    }

    /**
     * Create an instance of {@link FindOneUser }
     * 
     */
    public FindOneUser createFindOneUser() {
        return new FindOneUser();
    }

    /**
     * Create an instance of {@link FindOneUserResponse }
     * 
     */
    public FindOneUserResponse createFindOneUserResponse() {
        return new FindOneUserResponse();
    }

    /**
     * Create an instance of {@link LogIn }
     * 
     */
    public LogIn createLogIn() {
        return new LogIn();
    }

    /**
     * Create an instance of {@link LogInResponse }
     * 
     */
    public LogInResponse createLogInResponse() {
        return new LogInResponse();
    }

    /**
     * Create an instance of {@link RemoveAllUser }
     * 
     */
    public RemoveAllUser createRemoveAllUser() {
        return new RemoveAllUser();
    }

    /**
     * Create an instance of {@link RemoveAllUserResponse }
     * 
     */
    public RemoveAllUserResponse createRemoveAllUserResponse() {
        return new RemoveAllUserResponse();
    }

    /**
     * Create an instance of {@link RemoveUser }
     * 
     */
    public RemoveUser createRemoveUser() {
        return new RemoveUser();
    }

    /**
     * Create an instance of {@link RemoveUserResponse }
     * 
     */
    public RemoveUserResponse createRemoveUserResponse() {
        return new RemoveUserResponse();
    }

    /**
     * Create an instance of {@link UpdateUser }
     * 
     */
    public UpdateUser createUpdateUser() {
        return new UpdateUser();
    }

    /**
     * Create an instance of {@link UpdateUserPassword }
     * 
     */
    public UpdateUserPassword createUpdateUserPassword() {
        return new UpdateUserPassword();
    }

    /**
     * Create an instance of {@link UpdateUserPasswordResponse }
     * 
     */
    public UpdateUserPasswordResponse createUpdateUserPasswordResponse() {
        return new UpdateUserPasswordResponse();
    }

    /**
     * Create an instance of {@link UpdateUserResponse }
     * 
     */
    public UpdateUserResponse createUpdateUserResponse() {
        return new UpdateUserResponse();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "findAllUser")
    public JAXBElement<FindAllUser> createFindAllUser(FindAllUser value) {
        return new JAXBElement<FindAllUser>(_FindAllUser_QNAME, FindAllUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "findAllUserResponse")
    public JAXBElement<FindAllUserResponse> createFindAllUserResponse(FindAllUserResponse value) {
        return new JAXBElement<FindAllUserResponse>(_FindAllUserResponse_QNAME, FindAllUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "findOneUser")
    public JAXBElement<FindOneUser> createFindOneUser(FindOneUser value) {
        return new JAXBElement<FindOneUser>(_FindOneUser_QNAME, FindOneUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindOneUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "findOneUserResponse")
    public JAXBElement<FindOneUserResponse> createFindOneUserResponse(FindOneUserResponse value) {
        return new JAXBElement<FindOneUserResponse>(_FindOneUserResponse_QNAME, FindOneUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "logIn")
    public JAXBElement<LogIn> createLogIn(LogIn value) {
        return new JAXBElement<LogIn>(_LogIn_QNAME, LogIn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogInResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "logInResponse")
    public JAXBElement<LogInResponse> createLogInResponse(LogInResponse value) {
        return new JAXBElement<LogInResponse>(_LogInResponse_QNAME, LogInResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "removeAllUser")
    public JAXBElement<RemoveAllUser> createRemoveAllUser(RemoveAllUser value) {
        return new JAXBElement<RemoveAllUser>(_RemoveAllUser_QNAME, RemoveAllUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveAllUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "removeAllUserResponse")
    public JAXBElement<RemoveAllUserResponse> createRemoveAllUserResponse(RemoveAllUserResponse value) {
        return new JAXBElement<RemoveAllUserResponse>(_RemoveAllUserResponse_QNAME, RemoveAllUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "removeUser")
    public JAXBElement<RemoveUser> createRemoveUser(RemoveUser value) {
        return new JAXBElement<RemoveUser>(_RemoveUser_QNAME, RemoveUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "removeUserResponse")
    public JAXBElement<RemoveUserResponse> createRemoveUserResponse(RemoveUserResponse value) {
        return new JAXBElement<RemoveUserResponse>(_RemoveUserResponse_QNAME, RemoveUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "updateUser")
    public JAXBElement<UpdateUser> createUpdateUser(UpdateUser value) {
        return new JAXBElement<UpdateUser>(_UpdateUser_QNAME, UpdateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "updateUserPassword")
    public JAXBElement<UpdateUserPassword> createUpdateUserPassword(UpdateUserPassword value) {
        return new JAXBElement<UpdateUserPassword>(_UpdateUserPassword_QNAME, UpdateUserPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "updateUserPasswordResponse")
    public JAXBElement<UpdateUserPasswordResponse> createUpdateUserPasswordResponse(UpdateUserPasswordResponse value) {
        return new JAXBElement<UpdateUserPasswordResponse>(_UpdateUserPasswordResponse_QNAME, UpdateUserPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.api.tm.anikanov.ru/", name = "updateUserResponse")
    public JAXBElement<UpdateUserResponse> createUpdateUserResponse(UpdateUserResponse value) {
        return new JAXBElement<UpdateUserResponse>(_UpdateUserResponse_QNAME, UpdateUserResponse.class, null, value);
    }

}
