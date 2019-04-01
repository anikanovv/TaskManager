package ru.anikanov.tm.api.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IProjectRepository {

    @NotNull
    Project persist(@NotNull final Project p);

    Project merge(@NotNull Project p) throws SQLException;

    void remove(@NotNull final String name, @NotNull final String userId);

    void removeAll(@NotNull final String userId);

    @Nullable
    Project findOne(@NotNull final String name, @NotNull final String userId);

    @Nullable
    List<Project> findAll(@NotNull final String userId);

    @Nullable
    Project findByPartOfName(@NotNull final String partOfName, @NotNull final String userId) throws SQLException, ParseException;

    @Nullable
    Project findByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId) throws SQLException;
}
