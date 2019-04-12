package ru.anikanov.tm.repository;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.QueryParam;
import org.apache.deltaspike.data.api.Repository;
import ru.anikanov.tm.entity.Session;

@Repository
public interface SessionRepository extends EntityRepository<Session, String> {

    Session persist(Session session);

    @Query("SELECT session FROM Session session WHERE session.id = :id")
    Session findOne(@QueryParam("id") String sessionId);
}
