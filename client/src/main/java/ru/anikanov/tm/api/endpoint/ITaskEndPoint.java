package ru.anikanov.tm.api.endpoint;

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
 * 2019-03-19T18:56:16.558+03:00
 * Generated source version: 3.2.7
 *
 */
@WebService(targetNamespace = "http://endpoint.api.tm.anikanov.ru/", name = "ITaskEndPoint")
@XmlSeeAlso({ObjectFactory.class})
public interface ITaskEndPoint {

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/sortTaskByStartDateRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/sortTaskByStartDateResponse")
    @RequestWrapper(localName = "sortTaskByStartDate", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.SortTaskByStartDate")
    @ResponseWrapper(localName = "sortTaskByStartDateResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.SortTaskByStartDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.api.endpoint.Task> sortTaskByStartDate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/createTaskRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/createTaskResponse")
    @RequestWrapper(localName = "createTask", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.CreateTask")
    @ResponseWrapper(localName = "createTaskResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.CreateTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.api.endpoint.Task createTask(
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
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/findAllTaskRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/findAllTaskResponse")
    @RequestWrapper(localName = "findAllTask", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.FindAllTask")
    @ResponseWrapper(localName = "findAllTaskResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.FindAllTaskResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.api.endpoint.Task> findAllTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/findTaskByPartOfNameRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/findTaskByPartOfNameResponse")
    @RequestWrapper(localName = "findTaskByPartOfName", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.FindTaskByPartOfName")
    @ResponseWrapper(localName = "findTaskByPartOfNameResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.FindTaskByPartOfNameResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.api.endpoint.Task findTaskByPartOfName(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/sortTaskByStatusRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/sortTaskByStatusResponse")
    @RequestWrapper(localName = "sortTaskByStatus", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.SortTaskByStatus")
    @ResponseWrapper(localName = "sortTaskByStatusResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.SortTaskByStatusResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.api.endpoint.Task> sortTaskByStatus(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/removeTaskRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/removeTaskResponse")
    @RequestWrapper(localName = "removeTask", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.RemoveTask")
    @ResponseWrapper(localName = "removeTaskResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.RemoveTaskResponse")
    public void removeTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/findTaskByPartOfDescriptionRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/findTaskByPartOfDescriptionResponse")
    @RequestWrapper(localName = "findTaskByPartOfDescription", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.FindTaskByPartOfDescription")
    @ResponseWrapper(localName = "findTaskByPartOfDescriptionResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.FindTaskByPartOfDescriptionResponse")
    @WebResult(name = "return", targetNamespace = "")
    public ru.anikanov.tm.api.endpoint.Task findTaskByPartOfDescription(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/updateTaskRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/updateTaskResponse")
    @RequestWrapper(localName = "updateTask", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.UpdateTask")
    @ResponseWrapper(localName = "updateTaskResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.UpdateTaskResponse")
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
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/sortTaskByFinishDateRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/sortTaskByFinishDateResponse")
    @RequestWrapper(localName = "sortTaskByFinishDate", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.SortTaskByFinishDate")
    @ResponseWrapper(localName = "sortTaskByFinishDateResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.SortTaskByFinishDateResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<ru.anikanov.tm.api.endpoint.Task> sortTaskByFinishDate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebMethod
    @Action(input = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/removeAllTaskRequest", output = "http://endpoint.api.tm.anikanov.ru/ITaskEndPoint/removeAllTaskResponse")
    @RequestWrapper(localName = "removeAllTask", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.RemoveAllTask")
    @ResponseWrapper(localName = "removeAllTaskResponse", targetNamespace = "http://endpoint.api.tm.anikanov.ru/", className = "ru.anikanov.tm.api.endpoint.RemoveAllTaskResponse")
    public void removeAllTask(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
