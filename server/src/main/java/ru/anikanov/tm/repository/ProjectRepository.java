package ru.anikanov.tm.repository;

import org.apache.deltaspike.data.api.*;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Project;

import java.util.List;

@Repository
public interface ProjectRepository extends EntityRepository<Project, String> {
    void persist(@NotNull Project project);

    void remove(@NotNull Project project);

    Project merge(@NotNull Project project);

    @Modifying
    @Query("DELETE FROM Project project WHERE project.userId = :userId")
    void removeAll(@QueryParam("userId") @NotNull final String userId);

    @Query("SELECT project FROM Project project WHERE project.id = :id AND project.userId = :userId")
    Project findOne(@QueryParam("userId") @NotNull final String userId, @QueryParam("id") @NotNull final String id);

    @Query("SELECT project FROM Project project WHERE project.userId = :userId")
    List<Project> findAll(@QueryParam("userId") @NotNull final String userId);

    @Query("SELECT project FROM Project project WHERE project.userId = :userId AND project.name LIKE :name")
    Project findByPartOfName(@QueryParam("userId") @NotNull final String userId, @QueryParam("name") @NotNull final String partOfName);

    @Query("SELECT project FROM Project project WHERE project.userId = :userId AND project.description LIKE :description")
    Project findByPartOfDescription(@QueryParam("userId") @NotNull final String userId, @QueryParam("description") @NotNull final String description);
}
