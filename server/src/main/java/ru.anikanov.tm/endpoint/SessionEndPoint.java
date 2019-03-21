package ru.anikanov.tm.endpoint;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class SessionEndPoint {
    private ServiceLocator serviceLocator;

    public SessionEndPoint(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public SessionEndPoint() {
    }

    @WebMethod
    public Session createSession(@WebParam String userId) throws Exception {
        return serviceLocator.getSessionService().persist(userId);
    }

    @WebMethod
    public boolean validate(@WebParam Session session) throws Exception {
        return serviceLocator.getSessionService().validate(session);
    }

    @WebMethod
    public void remove(@WebParam Session session) throws Exception {
        serviceLocator.getSessionService().remove(session);
    }
}
