package ru.anikanov.tm.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import ru.anikanov.tm.entity.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, String> {
    Project save(@NotNull Project project);

    void delete(@NotNull Project project);

    void deleteAllById(@NotNull final String userId);

    Project findByUserIdAndId(@Param("userId") @NotNull final String userId, @Param("id") @NotNull final String id);

    List<Project> findAllByUserId(@Param("userId") @NotNull final String userId);

    Project findByUserIdAndName(@NotNull final String userId, @NotNull final String name);

    Project findByUserIdAndDescription(@Param("userId") @NotNull final String userId, @Param("description") @NotNull final String description);
}
