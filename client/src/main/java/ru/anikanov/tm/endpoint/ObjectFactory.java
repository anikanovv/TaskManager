
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

    private final static QName _FasterJsonSerialize_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "fasterJsonSerialize");
    private final static QName _FasterJsonSerializeResponse_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "fasterJsonSerializeResponse");
    private final static QName _FasterXmlSerialize_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "fasterXmlSerialize");
    private final static QName _FasterXmlSerializeResponse_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "fasterXmlSerializeResponse");
    private final static QName _JaxbJsonSerialize_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "jaxbJsonSerialize");
    private final static QName _JaxbJsonSerializeResponse_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "jaxbJsonSerializeResponse");
    private final static QName _JaxbXmlSerialize_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "jaxbXmlSerialize");
    private final static QName _JaxbXmlSerializeResponse_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "jaxbXmlSerializeResponse");
    private final static QName _StandartSerialize_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "standartSerialize");
    private final static QName _StandartSerializeResponse_QNAME = new QName("http://endpoint.tm.anikanov.ru/", "standartSerializeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.anikanov.tm.endpoint
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FasterJsonSerialize }
     * 
     */
    public FasterJsonSerialize createFasterJsonSerialize() {
        return new FasterJsonSerialize();
    }

    /**
     * Create an instance of {@link FasterJsonSerializeResponse }
     * 
     */
    public FasterJsonSerializeResponse createFasterJsonSerializeResponse() {
        return new FasterJsonSerializeResponse();
    }

    /**
     * Create an instance of {@link FasterXmlSerialize }
     * 
     */
    public FasterXmlSerialize createFasterXmlSerialize() {
        return new FasterXmlSerialize();
    }

    /**
     * Create an instance of {@link FasterXmlSerializeResponse }
     * 
     */
    public FasterXmlSerializeResponse createFasterXmlSerializeResponse() {
        return new FasterXmlSerializeResponse();
    }

    /**
     * Create an instance of {@link JaxbJsonSerialize }
     * 
     */
    public JaxbJsonSerialize createJaxbJsonSerialize() {
        return new JaxbJsonSerialize();
    }

    /**
     * Create an instance of {@link JaxbJsonSerializeResponse }
     * 
     */
    public JaxbJsonSerializeResponse createJaxbJsonSerializeResponse() {
        return new JaxbJsonSerializeResponse();
    }

    /**
     * Create an instance of {@link JaxbXmlSerialize }
     * 
     */
    public JaxbXmlSerialize createJaxbXmlSerialize() {
        return new JaxbXmlSerialize();
    }

    /**
     * Create an instance of {@link JaxbXmlSerializeResponse }
     * 
     */
    public JaxbXmlSerializeResponse createJaxbXmlSerializeResponse() {
        return new JaxbXmlSerializeResponse();
    }

    /**
     * Create an instance of {@link StandartSerialize }
     * 
     */
    public StandartSerialize createStandartSerialize() {
        return new StandartSerialize();
    }

    /**
     * Create an instance of {@link StandartSerializeResponse }
     * 
     */
    public StandartSerializeResponse createStandartSerializeResponse() {
        return new StandartSerializeResponse();
    }

    /**
     * Create an instance of {@link Session }
     * 
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterJsonSerialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "fasterJsonSerialize")
    public JAXBElement<FasterJsonSerialize> createFasterJsonSerialize(FasterJsonSerialize value) {
        return new JAXBElement<FasterJsonSerialize>(_FasterJsonSerialize_QNAME, FasterJsonSerialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterJsonSerializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "fasterJsonSerializeResponse")
    public JAXBElement<FasterJsonSerializeResponse> createFasterJsonSerializeResponse(FasterJsonSerializeResponse value) {
        return new JAXBElement<FasterJsonSerializeResponse>(_FasterJsonSerializeResponse_QNAME, FasterJsonSerializeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlSerialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "fasterXmlSerialize")
    public JAXBElement<FasterXmlSerialize> createFasterXmlSerialize(FasterXmlSerialize value) {
        return new JAXBElement<FasterXmlSerialize>(_FasterXmlSerialize_QNAME, FasterXmlSerialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FasterXmlSerializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "fasterXmlSerializeResponse")
    public JAXBElement<FasterXmlSerializeResponse> createFasterXmlSerializeResponse(FasterXmlSerializeResponse value) {
        return new JAXBElement<FasterXmlSerializeResponse>(_FasterXmlSerializeResponse_QNAME, FasterXmlSerializeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbJsonSerialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "jaxbJsonSerialize")
    public JAXBElement<JaxbJsonSerialize> createJaxbJsonSerialize(JaxbJsonSerialize value) {
        return new JAXBElement<JaxbJsonSerialize>(_JaxbJsonSerialize_QNAME, JaxbJsonSerialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbJsonSerializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "jaxbJsonSerializeResponse")
    public JAXBElement<JaxbJsonSerializeResponse> createJaxbJsonSerializeResponse(JaxbJsonSerializeResponse value) {
        return new JAXBElement<JaxbJsonSerializeResponse>(_JaxbJsonSerializeResponse_QNAME, JaxbJsonSerializeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbXmlSerialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "jaxbXmlSerialize")
    public JAXBElement<JaxbXmlSerialize> createJaxbXmlSerialize(JaxbXmlSerialize value) {
        return new JAXBElement<JaxbXmlSerialize>(_JaxbXmlSerialize_QNAME, JaxbXmlSerialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JaxbXmlSerializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "jaxbXmlSerializeResponse")
    public JAXBElement<JaxbXmlSerializeResponse> createJaxbXmlSerializeResponse(JaxbXmlSerializeResponse value) {
        return new JAXBElement<JaxbXmlSerializeResponse>(_JaxbXmlSerializeResponse_QNAME, JaxbXmlSerializeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StandartSerialize }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "standartSerialize")
    public JAXBElement<StandartSerialize> createStandartSerialize(StandartSerialize value) {
        return new JAXBElement<StandartSerialize>(_StandartSerialize_QNAME, StandartSerialize.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StandartSerializeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://endpoint.tm.anikanov.ru/", name = "standartSerializeResponse")
    public JAXBElement<StandartSerializeResponse> createStandartSerializeResponse(StandartSerializeResponse value) {
        return new JAXBElement<StandartSerializeResponse>(_StandartSerializeResponse_QNAME, StandartSerializeResponse.class, null, value);
    }

}
