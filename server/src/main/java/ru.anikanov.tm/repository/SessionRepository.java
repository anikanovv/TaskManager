package ru.anikanov.tm.repository;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class SessionRepository {
    private EntityManager entityManager;

    @Inject
    public SessionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Session persist(Session session) {
        entityManager.persist(session);
        return session;
    }

    @Nullable
    public Session findOne(String sessionId) {
        return entityManager.find(Session.class, sessionId);
    }
}
