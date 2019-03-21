
package ru.anikanov.tm.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.anikanov.tm.endpoint package. 
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

    private final static QName _Exception_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "Exception");
    private final static QName _CreateSession_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "createSession");
    private final static QName _CreateSessionResponse_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "createSessionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.anikanov.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link CreateSession }
     * 
     */
    public CreateSession createCreateSession() {
        return new CreateSession();
    }

    /**
     * Create an instance of {@link CreateSessionResponse }
     * 
     */
    public CreateSessionResponse createCreateSessionResponse() {
        return new CreateSessionResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSession }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "createSession")
    public JAXBElement<CreateSession> createCreateSession(CreateSession value) {
        return new JAXBElement<CreateSession>(_CreateSession_QNAME, CreateSession.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSessionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "createSessionResponse")
    public JAXBElement<CreateSessionResponse> createCreateSessionResponse(CreateSessionResponse value) {
        return new JAXBElement<CreateSessionResponse>(_CreateSessionResponse_QNAME, CreateSessionResponse.class, null, value);
    }

}
