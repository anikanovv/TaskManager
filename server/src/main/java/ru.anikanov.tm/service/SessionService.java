package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.repository.SessionRepository;

import java.util.UUID;

public class SessionService {

    @NotNull
    private SessionRepository sessionRepository;

    public SessionService(@NotNull final SessionRepository sr) {
        sessionRepository=sr;
    }

    public Session create(String userId) {
        Session session = new Session(userId, System.currentTimeMillis());
//        session.setUserId();
//        session.setId(UUID.randomUUID().toString());
//        session.setSignature(Integer.toString(session.getId().hashCode()));
//        session.setTimestamp(System.currentTimeMillis());
        return sessionRepository.create(session);
    }
   /* public Session persist(String userId) throws Exception {
        return sessionRepository.persist(new Session(userId));
    }

    public void check(Session session) throws Exception {
        if (session == null) throw new Exception();
        sessionRepository.check(session);
    }

    public void sign(Session session) throws Exception {
        if (session == null) throw new Exception();
        sessionRepository.sign(session);
    }

    public boolean validate(Session session) throws Exception {
        return sessionRepository.validate(session);
    }

    public void remove(Session session) throws Exception {
        sessionRepository.remove(session);
    }*/

}
