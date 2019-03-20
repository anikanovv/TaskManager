package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.Session;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SessionRepository {
    private Map<String, Session> sessionMap=new LinkedHashMap<>();
    public Session persist(Session session){
        return sessionMap.put(session.getId(),session);
    }

    public Session merge(Session session){
        Session oldSession=sessionMap.get(session.getId());
        if (oldSession==null){
            sessionMap.put(session.getId(),session);
            return session;
        }
        else {
            oldSession.setTimestamp(session.getTimestamp());
            return oldSession;
        }
    }

    public void remove(Session session) throws Exception {
         sessionMap.remove(session.getId());
    }

    public void removeAll() {
        sessionMap.clear();
    }

    public Session findOne(String id) {
        return sessionMap.get(id);
    }

    public List<Session> findAll() {
        ArrayList<Session> all=new ArrayList<>();
        sessionMap.forEach((k,v)->all.add(v));
        return all;
    }

}
