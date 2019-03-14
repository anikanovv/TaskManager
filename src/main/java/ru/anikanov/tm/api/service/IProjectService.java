package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface IProjectService {

    @Nullable
    Project persist(@NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish, @NotNull String userId);

    void merge(@NotNull String projectName, @NotNull String description, @NotNull String dateStart, @NotNull String dateFinish, @NotNull String userId);

    void remove(@NotNull String projectName, @NotNull String userId);

    void removeAll(@NotNull String userId);

    @Nullable
    Project findOne(@NotNull String name, @NotNull String userId);

    @Nullable
    List<Project> findAll(@NotNull String userId);
}
