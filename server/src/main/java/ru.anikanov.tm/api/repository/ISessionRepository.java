package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.utils.SignatureUtil;

import java.util.List;

public interface ISessionRepository extends IRepository {
    public Session persist(Session session) throws Exception;

    public void check(Session session) throws Exception;

    public void sign(Session session);

    public boolean validate(Session session) throws Exception;

    public void remove(Session session) throws Exception;

}
