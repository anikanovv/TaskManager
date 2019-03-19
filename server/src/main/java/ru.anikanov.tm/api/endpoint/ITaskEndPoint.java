package ru.anikanov.tm.api.endpoint;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ITaskEndPoint {
    @WebMethod
    @Nullable
    Task createTask(@Nullable final String projectId, @Nullable final String taskName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId);

    @WebMethod
    void updateTask(@Nullable final String taskId, @Nullable final String taskName, @Nullable final String description, @Nullable final String dateStart, @Nullable final String dateFinish, @NotNull final String userId);

    @WebMethod
    void removeTask(@Nullable final String taskId, @NotNull final String userId);

    @WebMethod
    void removeAllTask(@NotNull final String userId);

    @WebMethod
    @Nullable
    List<Task> findAllTask(@NotNull final String userId);

    @WebMethod
    @Nullable
    List<Task> sortTaskByStartDate(@NotNull final String userId);

    @WebMethod
    @Nullable
    List<Task> sortTaskByFinishDate(@NotNull final String userId);

    @WebMethod
    @Nullable
    List<Task> sortTaskByStatus(@NotNull final String userId);

    @WebMethod
    @Nullable
    Task findTaskByPartOfName(@NotNull final String partOfName, @NotNull final String userId);

    @WebMethod
    @Nullable
    Task findTaskByPartOfDescription(@NotNull final String partOfDescription, @NotNull final String userId);
}
