package ru.anikanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.enumeration.Status;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor

public class ProjectDto {
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

    public ProjectDto(@NotNull Project project) {
        name = project.getName();
        description = project.getDescription();
        dateBegin = project.getStartDate();
        dateEnd = project.getEndDate();
        status = project.getStatus();
    }
}
