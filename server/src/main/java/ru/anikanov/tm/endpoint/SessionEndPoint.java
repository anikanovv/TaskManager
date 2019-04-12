package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.entity.Session;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
@NoArgsConstructor
public class SessionEndPoint {
    @Inject
    private ISessionService sessionService;

    @WebMethod
    public Session createSession(@WebParam final String userId) {
        return sessionService.persist(userId);
    }

    @WebMethod
    public boolean validate(@WebParam final Session session) {
        return sessionService.validate(session);
    }

}

