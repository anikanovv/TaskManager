package ru.anikanov.tm.service;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.utils.SignatureUtil;

import java.util.UUID;

@Service
@Transactional
@NoArgsConstructor
public class SessionService implements ISessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Session persist(@Nullable final String userId) {
        if (userId == null || userId.isEmpty()) return null;
        @Nullable Session session = new Session(userId, System.currentTimeMillis());
        session.setId(UUID.randomUUID().toString());
        session.setTimestamp(System.currentTimeMillis());
        session.setUserId(userId);
        session.setSignature(SignatureUtil.sign(session, "salt", 22));
        sessionRepository.save(session);
        return session;
    }

    public Session findOne(@Nullable final String sessionId) {

        if (sessionId == null || sessionId.isEmpty()) return null;
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
