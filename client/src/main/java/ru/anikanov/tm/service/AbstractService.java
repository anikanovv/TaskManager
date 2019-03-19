package ru.anikanov.tm.service;

import ru.anikanov.tm.entity.AbstractEntity;

import java.util.List;

public abstract class AbstractService<T extends AbstractEntity> {

    abstract void removeAll(String userId);

    abstract List<T> findAll(String userId);
}
