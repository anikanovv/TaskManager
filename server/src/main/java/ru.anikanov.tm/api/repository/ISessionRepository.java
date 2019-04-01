package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.Session;

public interface ISessionRepository {
    Session persist(Session session) throws Exception;

    void check(Session session) throws Exception;

    void sign(Session session);

    boolean validate(Session session) throws Exception;

    void remove(Session session) throws Exception;

}
