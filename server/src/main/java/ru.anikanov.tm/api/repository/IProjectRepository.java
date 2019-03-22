package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository {

    @NotNull
    Project persist(@NotNull final Project p);

    Project merge(@NotNull Project p);

    void remove(@NotNull final String name);

    void removeAll();

    @Nullable
    Project findOne(@NotNull final String name);

    @Nullable
    List<Project> findAll();

    @Nullable
    List<Project> sortedByStartDate();

    @Nullable
    List<Project> sortedByFinishDate();

    @Nullable
    List<Project> sortedByStatus();

    @Nullable
    Project findByPartOfName(@NotNull final String partOfName);

    @Nullable
    Project findByPartOfDescription(@NotNull final String partOfDescription);
}
