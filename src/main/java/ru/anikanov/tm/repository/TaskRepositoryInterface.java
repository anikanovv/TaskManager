package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.Task;

import java.util.List;

public interface TaskRepositoryInterface {
    Task persist();

    void merge();

    void remove();

    void removeall();

    Task findone();

    List<Task> findall();
}
