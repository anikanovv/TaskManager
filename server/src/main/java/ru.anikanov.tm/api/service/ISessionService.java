package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;

public interface ISessionService {
    Session persist(String userId);

    Session findOne(String sessionId);

    boolean validate(@Nullable final Session session);

}
