package ru.anikanov.tm.service;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.api.repository.IProjectRepository;
import ru.anikanov.tm.api.repository.ISessionRepository;
import ru.anikanov.tm.api.repository.ITaskRepository;
import ru.anikanov.tm.api.repository.IUserRepository;
import ru.anikanov.tm.entity.Session;

import java.util.List;

public class SessionService extends AbstractService {
    @NotNull
    private IProjectRepository projectRepository;
    @NotNull
    private ITaskRepository taskRepository;
    @NotNull
    private IUserRepository userRepository;
    @NotNull
    private ISessionRepository sessionRepository;

    public SessionService(@NotNull final IProjectRepository pr, @NotNull final ITaskRepository tr, @NotNull final IUserRepository ur,@NotNull final ISessionRepository sr) {
        projectRepository = pr;
        taskRepository = tr;
        userRepository = ur;
        sessionRepository=sr;
    }
    @Override
    void removeAll(String userId) {

    }

    @Override
    List findAll(String userId) {
        return null;
    }

    public Session persist(Session session){
        if (session.getUserId().isEmpty()) return null;
        if (session.getId().isEmpty()) return null;
        return sessionRepository.persist(session);
    }
}
