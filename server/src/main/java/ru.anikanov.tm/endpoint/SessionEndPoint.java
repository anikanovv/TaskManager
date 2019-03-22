package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@NoArgsConstructor
@WebService
public class SessionEndPoint {
    private ServiceLocator serviceLocator;

    public SessionEndPoint(final ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @WebMethod
    public Session createSession(@WebParam final String userId) {
        return serviceLocator.getSessionService().create(userId);
    }

   /* @WebMethod
    public boolean validate(@WebParam Session session) throws Exception {
        return serviceLocator.getSessionService().validate(session);
    }

    @WebMethod
    public void remove(@WebParam Session session) throws Exception {
        serviceLocator.getSessionService().remove(session);
    }*/
}
