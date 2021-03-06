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
 * 2019-04-16T14:02:57.940+03:00
 * Generated source version: 3.2.7
 */
@WebService(targetNamespace = "http://endpoint.tm.anikanov.ru/", name = "DomainEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface DomainEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/DomainEndPoint/fasterXmlSerializeRequest", output = "http://endpoint.tm.anikanov.ru/DomainEndPoint/fasterXmlSerializeResponse")
    @RequestWrapper(localName = "fasterXmlSerialize", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FasterXmlSerialize")
    @ResponseWrapper(localName = "fasterXmlSerializeResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FasterXmlSerializeResponse")
    void fasterXmlSerialize(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/DomainEndPoint/jaxbJsonSerializeRequest", output = "http://endpoint.tm.anikanov.ru/DomainEndPoint/jaxbJsonSerializeResponse")
    @RequestWrapper(localName = "jaxbJsonSerialize", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.JaxbJsonSerialize")
    @ResponseWrapper(localName = "jaxbJsonSerializeResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.JaxbJsonSerializeResponse")
    void jaxbJsonSerialize(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/DomainEndPoint/jaxbXmlSerializeRequest", output = "http://endpoint.tm.anikanov.ru/DomainEndPoint/jaxbXmlSerializeResponse")
    @RequestWrapper(localName = "jaxbXmlSerialize", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.JaxbXmlSerialize")
    @ResponseWrapper(localName = "jaxbXmlSerializeResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.JaxbXmlSerializeResponse")
    void jaxbXmlSerialize(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/DomainEndPoint/fasterJsonSerializeRequest", output = "http://endpoint.tm.anikanov.ru/DomainEndPoint/fasterJsonSerializeResponse")
    @RequestWrapper(localName = "fasterJsonSerialize", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FasterJsonSerialize")
    @ResponseWrapper(localName = "fasterJsonSerializeResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FasterJsonSerializeResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.anikanov.tm.endpoint.Domain fasterJsonSerialize(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/DomainEndPoint/standartSerializeRequest", output = "http://endpoint.tm.anikanov.ru/DomainEndPoint/standartSerializeResponse")
    @RequestWrapper(localName = "standartSerialize", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.StandartSerialize")
    @ResponseWrapper(localName = "standartSerializeResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.StandartSerializeResponse")
    void standartSerialize(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    );
}
