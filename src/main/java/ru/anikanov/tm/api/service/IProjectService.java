package ru.anikanov.tm.api.service;

import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface IProjectService {

    Project persist(String projectName, String description, String dateStart, String dateFinish, String userId);

    void merge(String projectName, String description, String dateStart, String dateFinish, String userId);

    void remove(String projectName, String userId);

    void removeAll(String userId);

    Project findOne(String name, String userId);

    List<Project> findAll(String userId);
}