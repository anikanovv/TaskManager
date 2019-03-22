package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.ISessionService;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.repository.SessionRepository;
import ru.anikanov.tm.utils.SignatureUtil;

import java.util.UUID;

public class SessionService implements ISessionService {

    @NotNull
    private SessionRepository sessionRepository;

    public SessionService(@NotNull final SessionRepository sr) {
        sessionRepository=sr;
    }

    public Session create(String userId) {
        Session session = new Session(userId, System.currentTimeMillis());
        session.setUserId(userId);
        session.setId(UUID.randomUUID().toString());
        session.setSignature(SignatureUtil.sign(session, "salt", 22));
        session.setTimestamp(System.currentTimeMillis());
        return sessionRepository.create(session);
    }

    public boolean validate(@Nullable final Session session) {
        if (session == null) return false;
        if (session.getSignature() == null) return false;
        if (session.getTimestamp() == null) return false;
        if (session.getId() == null) return false;
        Session clone = null;
        try {
            clone = session.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        clone.setSignature(SignatureUtil.sign(session, "salt", 22));
        if (session.getSignature() != null) {
            return (session.getSignature().equals(clone.getSignature()));
        }
        return false;
    }

}
