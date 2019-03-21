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
 * 2019-03-21T18:19:23.186+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.tm.anikanov.ru/", name = "TaskEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface TaskEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/removeTaskRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/removeTaskResponse")
    @RequestWrapper(localName = "removeTask", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveTask")
    @ResponseWrapper(localName = "removeTaskResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveTaskResponse")
    public void removeTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findTaskByPartOfDescriptionRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findTaskByPartOfDescriptionResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findTaskByPartOfDescription/Fault/Exception")})
    @RequestWrapper(localName = "findTaskByPartOfDescription", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindTaskByPartOfDescription")
    @ResponseWrapper(localName = "findTaskByPartOfDescriptionResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindTaskByPartOfDescriptionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Task findTaskByPartOfDescription(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/updateTaskRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/updateTaskResponse")
    @RequestWrapper(localName = "updateTask", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateTask")
    @ResponseWrapper(localName = "updateTaskResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.UpdateTaskResponse")
    public void updateTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByFinishDateRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByFinishDateResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByFinishDate/Fault/Exception")})
    @RequestWrapper(localName = "sortTaskByFinishDate", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortTaskByFinishDate")
    @ResponseWrapper(localName = "sortTaskByFinishDateResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortTaskByFinishDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Task> sortTaskByFinishDate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/removeAllTaskRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/removeAllTaskResponse")
    @RequestWrapper(localName = "removeAllTask", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveAllTask")
    @ResponseWrapper(localName = "removeAllTaskResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.RemoveAllTaskResponse")
    public void removeAllTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findTaskByPartOfNameRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findTaskByPartOfNameResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findTaskByPartOfName/Fault/Exception")})
    @RequestWrapper(localName = "findTaskByPartOfName", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindTaskByPartOfName")
    @ResponseWrapper(localName = "findTaskByPartOfNameResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindTaskByPartOfNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Task findTaskByPartOfName(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByStatusRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByStatusResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByStatus/Fault/Exception")})
    @RequestWrapper(localName = "sortTaskByStatus", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortTaskByStatus")
    @ResponseWrapper(localName = "sortTaskByStatusResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortTaskByStatusResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Task> sortTaskByStatus(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findAllTaskRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/findAllTaskResponse")
    @RequestWrapper(localName = "findAllTask", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindAllTask")
    @ResponseWrapper(localName = "findAllTaskResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.FindAllTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Task> findAllTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByStartDateRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByStartDateResponse", fault = {@FaultAction(className = Exception_Exception.class, value = "http://endpoint.tm.anikanov.ru/TaskEndPoint/sortTaskByStartDate/Fault/Exception")})
    @RequestWrapper(localName = "sortTaskByStartDate", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortTaskByStartDate")
    @ResponseWrapper(localName = "sortTaskByStartDateResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.SortTaskByStartDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.endpoint.Task> sortTaskByStartDate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    ) throws Exception_Exception;

    @WebMethod
    @Action(input = "http://endpoint.tm.anikanov.ru/TaskEndPoint/createTaskRequest", output = "http://endpoint.tm.anikanov.ru/TaskEndPoint/createTaskResponse")
    @RequestWrapper(localName = "createTask", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateTask")
    @ResponseWrapper(localName = "createTaskResponse", targetNamespace = "http://endpoint.tm.anikanov.ru/", className = "ru.anikanov.tm.endpoint.CreateTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.endpoint.Task createTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        java.lang.String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        java.lang.String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        java.lang.String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        java.lang.String arg5
    );
}
