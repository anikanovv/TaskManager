package ru.anikanov.tm.repository;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;

import javax.persistence.EntityManager;
import java.sql.Connection;

public class SessionRepository {
    private Connection connection;
    private EntityManager entityManager;

    public SessionRepository(@Nullable final EntityManager entityManager) {
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
