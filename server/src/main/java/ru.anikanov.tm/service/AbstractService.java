package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.AbstractEntity;
import ru.anikanov.tm.entity.Session;

import java.util.List;

public abstract class AbstractService<T extends AbstractEntity> {

    abstract void removeAll(String currentSession);

    abstract List<T> findAll(String currentSession);
}
