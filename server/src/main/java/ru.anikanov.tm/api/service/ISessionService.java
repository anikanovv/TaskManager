package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Session;

public interface ISessionService {
    Session create(String userId);

    Session findOne(String userId);

    boolean validate(@Nullable final Session session);

}
