package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Session;

public interface IDomainService {
    void fasterJson(@NotNull final Session session);

    void fasterXml(@NotNull final Session session);

    void jaxbJson(@NotNull final Session session);

    void jaxbXml(@NotNull final Session session);

    void standartSerialize(@NotNull final Session session);
}
