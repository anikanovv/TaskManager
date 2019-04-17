package ru.anikanov.tm.endpoint;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.entity.Session;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Service
@WebService
@NoArgsConstructor
public class SessionEndPoint {
    @Autowired
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

