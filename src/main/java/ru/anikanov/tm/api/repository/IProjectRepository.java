package ru.anikanov.tm.api.repository;

import ru.anikanov.tm.entity.Project;
import java.util.List;

public interface IProjectRepository extends IRepository {

    Project persist(Project p);

    void merge(Project p) throws Exception;

    void remove(String name);

    void removeAll();

    Project findOne(String name);

    List<Project> findAll();

}
