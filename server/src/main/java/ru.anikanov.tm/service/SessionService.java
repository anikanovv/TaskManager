package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.utils.SignatureUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.UUID;

public class SessionService implements ISessionService {
    private EntityManagerFactory factory;

    public SessionService(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public Session persist(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return null;
        @Nullable Session session = new Session(userId, System.currentTimeMillis());
        session.setId(UUID.randomUUID().toString());
        session.setTimestamp(System.currentTimeMillis());
        session.setUserId(userId);
        session.setSignature(SignatureUtil.sign(session, "salt", 22));
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        try {
            @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
            entityManager.getTransaction().begin();
            sessionRepository.persist(session);
            entityManager.getTransaction().commit();
            return session;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Session findOne(@Nullable final String sessionId) {

        if (sessionId == null || sessionId.isEmpty()) return null;
        @NotNull final EntityManager entityManager = factory.createEntityManager();
        @NotNull final SessionRepository sessionRepository = new SessionRepository(entityManager);
        return sessionRepository.findOne(sessionId);
    }

    public boolean validate(@Nullable final Session session) {
        if (session == null) return false;
        if (session.getId() == null) return false;
        @Nullable Session clone = findOne(session.getId());
        if (clone == null) return false;
        clone.setSignature(null);
        clone.setSignature(SignatureUtil.sign(clone, "salt", 22));
        if (session.getSignature() != null) {
            return (session.getSignature().equals(clone.getSignature()));
        }
        return false;
    }

}
