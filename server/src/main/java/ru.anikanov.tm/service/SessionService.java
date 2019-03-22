package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.utils.SignatureUtil;

import java.util.UUID;

public class SessionService {

    @NotNull
    private SessionRepository sessionRepository;

    public SessionService(@NotNull final SessionRepository sr) {
        sessionRepository=sr;
    }

    public Session create(String userId) {
        Session session = new Session(userId, System.currentTimeMillis());
        session.setUserId(userId);
        session.setId(UUID.randomUUID().toString());
        session.setSignature(SignatureUtil.sign(session, "salt", 22));
        session.setTimestamp(System.currentTimeMillis());
        return sessionRepository.create(session);
    }

    public boolean check(Session session, String userId) throws CloneNotSupportedException {
        Session clone = session.clone();
        clone.setUserId(userId);
        clone.setSignature(SignatureUtil.sign(session, "salt", 22));
        if (session.getSignature() != null) {
            return (session.getSignature().equals(clone.getSignature()));
        }
        return false;
    }
    /*
    public boolean validate(Session session,String userId) throws Exception {
        if (check(session,userId))
    }*/
   /* public Session persist(String userId) throws Exception {
        return sessionRepository.persist(new Session(userId));
    }


    public void sign(Session session) throws Exception {
        if (session == null) throw new Exception();
        sessionRepository.sign(session);
    }


    public void remove(Session session) throws Exception {
        sessionRepository.remove(session);
    }*/


}
