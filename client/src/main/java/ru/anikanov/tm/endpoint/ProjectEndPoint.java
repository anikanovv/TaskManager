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
 * 2019-03-21T16:52:03.529+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.anikanov.ru/", name = "ProjectEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ProjectEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findAllProjectRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findAllProjectResponse")
    @RequestWrapper(localName = "findAllProject", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindAllProject")
    @ResponseWrapper(localName = "findAllProjectResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindAllProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Project> findAllProject(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/removeAllProjectRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/removeAllProjectResponse")
    @RequestWrapper(localName = "removeAllProject", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveAllProject")
    @ResponseWrapper(localName = "removeAllProjectResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveAllProjectResponse")
    public void removeAllProject(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findProjectByPartOfNameProjectRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findProjectByPartOfNameProjectResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findProjectByPartOfNameProject/Fault/Exception")})
    @RequestWrapper(localName = "findProjectByPartOfNameProject", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindProjectByPartOfNameProject")
    @ResponseWrapper(localName = "findProjectByPartOfNameProjectResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindProjectByPartOfNameProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Project findProjectByPartOfNameProject(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/removeProjectRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/removeProjectResponse")
    @RequestWrapper(localName = "removeProject", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveProject")
    @ResponseWrapper(localName = "removeProjectResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveProjectResponse")
    public void removeProject(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByStatusRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByStatusResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByStatus/Fault/Exception")})
    @RequestWrapper(localName = "sortProjectByStatus", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortProjectByStatus")
    @ResponseWrapper(localName = "sortProjectByStatusResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortProjectByStatusResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Project> sortProjectByStatus(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/updateProjectRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/updateProjectResponse")
    @RequestWrapper(localName = "updateProject", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateProject")
    @ResponseWrapper(localName = "updateProjectResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateProjectResponse")
    public void updateProject(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/createProjectRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/createProjectResponse")
    @RequestWrapper(localName = "createProject", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateProject")
    @ResponseWrapper(localName = "createProjectResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateProjectResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Project createProject(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByStartDateRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByStartDateResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByStartDate/Fault/Exception")})
    @RequestWrapper(localName = "sortProjectByStartDate", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortProjectByStartDate")
    @ResponseWrapper(localName = "sortProjectByStartDateResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortProjectByStartDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Project> sortProjectByStartDate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findProjectByPartOfDescriptionRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findProjectByPartOfDescriptionResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/findProjectByPartOfDescription/Fault/Exception")})
    @RequestWrapper(localName = "findProjectByPartOfDescription", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindProjectByPartOfDescription")
    @ResponseWrapper(localName = "findProjectByPartOfDescriptionResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindProjectByPartOfDescriptionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Project findProjectByPartOfDescription(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByFinishDateRequest", output = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByFinishDateResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/ProjectEndPoint/sortProjectByFinishDate/Fault/Exception")})
    @RequestWrapper(localName = "sortProjectByFinishDate", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortProjectByFinishDate")
    @ResponseWrapper(localName = "sortProjectByFinishDateResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortProjectByFinishDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Project> sortProjectByFinishDate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception_Exception;
}
