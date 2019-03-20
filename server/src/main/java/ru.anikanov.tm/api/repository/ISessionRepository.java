package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.Session;

import java.util.List;

public interface ISessionRepository extends IRepository {
    Session persist(Session session);

    Session merge(Session session);

    void remove(Session session) throws Exception ;

    void removeAll();

    Session findOne(String id) ;

    List<Session> findAll() ;

}
