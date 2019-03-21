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
 * 2019-03-21T16:52:03.999+03:00
 * Generated source version: 3.2.7
 */
@WebService(targetNamespace = "http://endpoint.tm.anikanov.ru/", name = "SessionEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface SessionEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/SessionEndPoint/validateRequest", output = "http://endpoint.tm.anikanov.ru/SessionEndPoint/validateResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/SessionEndPoint/validate/Fault/Exception")})
    @RequestWrapper(localName = "validate", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.Validate")
    @ResponseWrapper(localName = "validateResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.ValidateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public boolean validate(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/SessionEndPoint/removeRequest", output = "http://endpoint.tm.anikanov.ru/SessionEndPoint/removeResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/SessionEndPoint/remove/Fault/Exception")})
    @RequestWrapper(localName = "remove", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.Remove")
    @ResponseWrapper(localName = "removeResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveResponse")
    public void remove(
            @WebParam(name = "arg0", targetNamespace = "")
                    ru.anikanov.tm.endpoint.Session arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/SessionEndPoint/createSessionRequest", output = "http://endpoint.tm.anikanov.ru/SessionEndPoint/createSessionResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/SessionEndPoint/createSession/Fault/Exception")})
    @RequestWrapper(localName = "createSession", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateSession")
    @ResponseWrapper(localName = "createSessionResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateSessionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Session createSession(
            @WebParam(name = "arg0", targetNamespace = "")
                    java.lang.String arg0
    ) throws Exception_Exception;
}
