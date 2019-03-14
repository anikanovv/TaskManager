package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface IProjectRepository extends IRepository {

    @NotNull
    Project persist(@NotNull final Project p);

    void merge(@NotNull Project p) throws Exception;

    void remove(@NotNull final String name);

    void removeAll();

    @Nullable
    Project findOne(@NotNull final String name);

    @Nullable
    List<Project> findAll();

}
