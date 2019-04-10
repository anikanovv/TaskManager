package ru.anikanov.tm.service;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.repository.SessionRep;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.utils.SignatureUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;

@Transactional
@ApplicationScoped
public class SessionService implements ISessionService {
    @Inject
    private SessionRepository sessionRepository;
    @Inject
    private SessionRep sessionRep;
    @Inject
    private EntityManager entityManager;

    public Session persist(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return null;
        @Nullable Session session = new Session(userId, System.currentTimeMillis());
        session.setId(UUID.randomUUID().toString());
        session.setTimestamp(System.currentTimeMillis());
        session.setUserId(userId);
        session.setSignature(SignatureUtil.sign(session, "salt", 22));
//        final SessionRepository sessionRepository = new SessionRepository(entityManager);
//        try {
//            entityManager.getTransaction().begin();
        sessionRep.persist(session);
//            entityManager.getTransaction().commit();
            return session;
//        } catch (Exception e) {
//            entityManager.getTransaction().rollback();
//            return null;
//        }
    }

    @Override
    public Session findOne(@Nullable final String sessionId) {

        if (sessionId == null || sessionId.isEmpty()) return null;
//        final SessionRepository sessionRepository = new SessionRepository(entityManager);
        return sessionRep.findOne(sessionId);
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
