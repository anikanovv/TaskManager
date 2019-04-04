package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.Session;

public interface ISessionRepository {
    Session persist(Session session);

    void check(Session session);

    void sign(Session session);

    boolean validate(Session session);

    void remove(Session session);

}
