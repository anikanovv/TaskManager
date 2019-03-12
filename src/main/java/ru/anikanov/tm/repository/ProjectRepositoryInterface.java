package ru.anikanov.tm.repository;

import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface ProjectRepositoryInterface {
    Project persist();

    void merge();

    void remove();

    void removeall();

    Project findone();

    List<Project> findall();

}
