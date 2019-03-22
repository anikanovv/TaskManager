package ru.anikanov.tm.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.2.7
 * 2019-03-22T13:51:09.115+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.anikanov.ru/", name = "SessionEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface SessionEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/SessionEndPoint/createSessionRequest", output = "http://endpoint.tm.anikanov.ru/SessionEndPoint/createSessionResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/SessionEndPoint/createSession/Fault/Exception")})
    @RequestWrapper(localName = "createSession", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateSession")
    @ResponseWrapper(localName = "createSessionResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    ru.anikanov.tm.endpoint.Session createSession(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0
    ) throws Exception_Exception;
}
