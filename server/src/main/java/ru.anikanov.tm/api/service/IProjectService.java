package ru.anikanov.tm.api.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IProjectService {

    @Nullable
    Project persist(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId);

    void merge(@Nullable final String projectName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId);

    void remove(@Nullable final String projectName, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    @Nullable
    Project findOne(@Nullable final String name, @NotNull final String userId);

    @Nullable
    List<Project> findAll(@NotNull final String userId);

    @Nullable
    List<Project> sortedByStartDate(@NotNull final String userId);

    @Nullable
    List<Project> sortedByFinishDate(@NotNull final String userId);

    @Nullable
    List<Project> sortedByStatus(@NotNull final String userId);

    @Nullable
    Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) throws SQLException, ParseException;

    @Nullable
    Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) throws SQLException;
}
