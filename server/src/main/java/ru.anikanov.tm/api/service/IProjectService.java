package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;

import java.util.List;

public interface IProjectService {

    @Nullable
    Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final Session session);

    void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final Session session);

    void remove(@Nullable final String projectName, @NotNull final Session session);

    void removeAll(@NotNull final Session session);

    @Nullable
    Project findOne(@Nullable final String name, @NotNull final Session session);

    @Nullable
    List<Project> findAll(@NotNull final Session session);

    @Nullable
    List<Project> sortedByStartDate(@NotNull final Session session);

    @Nullable
    List<Project> sortedByFinishDate(@NotNull final Session session);

    @Nullable
    List<Project> sortedByStatus(@NotNull final Session session);

    @Nullable
    Project findByPartOfName(@NotNull final String partOfName, @NotNull final Session session);

    @Nullable
    Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final Session session);
}
