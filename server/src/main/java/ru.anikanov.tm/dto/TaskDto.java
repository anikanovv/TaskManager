package ru.anikanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.enumeration.Status;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class TaskDto {
    @Nullable
    private String name;
    @Nullable
    private String description;
    @Nullable
    private Date dateBegin;
    @Nullable
    private Date dateEnd;
    @Nullable
    private Status status;

    public TaskDto(@NotNull Task task) {
        name = task.getName();
        description = task.getDescription();
        dateBegin = task.getStartDate();
        dateEnd = task.getEndDate();
        status = task.getStatus();
    }
}
