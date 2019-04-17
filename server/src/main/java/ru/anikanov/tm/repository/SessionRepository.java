package ru.anikanov.tm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.anikanov.tm.entity.Session;

public interface SessionRepository extends JpaRepository<Session, String> {
    Session save(Session session);

    @Query("SELECT session FROM Session session WHERE session.id = :id")
    Session findOne(@Param("id") String id);
}
