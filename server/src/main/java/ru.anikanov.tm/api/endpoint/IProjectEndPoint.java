package ru.anikanov.tm.api.endpoint;

import ru.anikanov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IProjectEndPoint {
    @WebMethod
    Project createProject(String name, String description, String startDate, String endDate, String userId);
    @WebMethod
    void updateProject(String name, String description, String startDate, String endDate, String userId);
    @WebMethod
    void removeProject(String name, String userId);
    @WebMethod
    void removeAllProject(String userId);
    @WebMethod
    Project findProjectByPartOfNameProject(String partOfName, String userId);
    @WebMethod
    Project findProjectByPartOfDescription(String partOfDescription, String userId);
    @WebMethod
    List<Project> sortProjectByStartDate(String userId);
    @WebMethod
    List<Project> sortProjectByFinishDate(String userId);
    @WebMethod
    List<Project> sortProjectByStatus(String userId);
    @WebMethod
    List<Project> findAllProject(String userId);
}
