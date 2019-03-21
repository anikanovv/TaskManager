package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.utils.SignatureUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class SessionRepository {
    private Map<String, Session> sessionMap=new LinkedHashMap<>();

    /* public Session persist(Session session){
 //        sign(session);
         session.setTimestamp(System.currentTimeMillis());
         session.setSignature(SignatureUtil.sign(session.getId(), "salt", 11));
         return sessionMap.put(session.getId(),session);
     }

     public void check(Session session) {
         session.setSignature(null);
         sign(session);
     }

     public void sign(Session session) {
         String signature = SignatureUtil.sign(session.getId(), "salt", 11);
         session.setSignature(signature);
     }

     public boolean validate(Session session) throws Exception {
         if (session == null) throw new Exception();
         if (session.getId() == null) throw new Exception();
         if (session.getTimestamp() == null) throw new Exception();
         if (session.getSignature() == null) throw new Exception();
        *//* Session newSession = session.clone();
        check(newSession);*//*
        return session.getSignature().equals(session.getSignature());
    }

    public void remove(Session session) throws Exception {
         sessionMap.remove(session.getId());
    }
*/
    public Session create(Session session) {
        sessionMap.put(session.getId(), session);
        return session;
    }
}
