package ru.anikanov.tm.endpoint;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.endpoint.IProjectEndPoint;
import ru.anikanov.tm.entity.Project;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;
@WebService
public class ProjectEndPoint implements IProjectEndPoint {
    private ServiceLocator serviceLocator;
    public ProjectEndPoint(ServiceLocator serviceLocator){
        this.serviceLocator=serviceLocator;
    }
    @WebMethod
    public Project createProject(String name, String description, String startDate, String endDate, String userId){
        return serviceLocator.getProjectService().persist(name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void updateProject(String name, String description, String startDate, String endDate, String userId){
       serviceLocator.getProjectService().merge(name, description, startDate, endDate, userId);
    }
    @WebMethod
    public void removeProject(String name, String userId){
        serviceLocator.getProjectService().remove(name,userId);
    }
    @WebMethod
    public void removeAllProject(String userId){
        serviceLocator.getProjectService().removeAll(userId);
    }
    @WebMethod
    public Project findProjectByPartOfNameProject(String partOfName, String userId){
        return serviceLocator.getProjectService().findByPartOfName(partOfName,userId);
    }
    @WebMethod
    public Project findProjectByPartOfDescription(String partOfDescription, String userId){
        return serviceLocator.getProjectService().findByPartOfDescription(partOfDescription, userId);
    }
    @WebMethod
    public List<Project> sortProjectByStartDate(String userId){
        return serviceLocator.getProjectService().sortedByStartDate(userId);
    }
    @WebMethod
    public List<Project> sortProjectByFinishDate(String userId){
        return serviceLocator.getProjectService().sortedByFinishDate(userId);
    }
    @WebMethod
    public List<Project> sortProjectByStatus(String userId){
        return serviceLocator.getProjectService().sortedByStatus(userId);
    }
    @WebMethod
    public List<Project> findAllProject(String userId){
        return serviceLocator.getProjectService().findAll(userId);
    }
}
