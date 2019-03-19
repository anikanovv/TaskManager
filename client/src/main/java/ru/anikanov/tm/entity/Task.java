package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
public class Task extends AbstractEntity {
    @NotNull
    private String projectId;
    @Nullable
    private String taskName;
    @Nullable
    private String taskDescription;
    @NotNull
    private Date startDate;
    @Nullable
    private Date endDate;
    @NotNull
    private String userId;
    @NotNull
    private Status status;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Task(@NotNull String projectId, @Nullable String name, @Nullable String description, @Nullable String startDate, @Nullable String endDate, @NotNull String userId) throws Exception {
        this.projectId = projectId;
        this.taskName = name;
        this.taskDescription = description;
        this.startDate = format.parse(startDate);
        this.endDate = format.parse(endDate);
        this.userId = userId;
        status = Status.SCHEDULED;
    }

    public String getStart() {
        return format.format(startDate);
    }

    public void setStart(String start) throws ParseException {
        this.startDate = format.parse(start);
    }

    public String getEnd() {
        return format.format(startDate);
    }

    public void setEnd(String end) throws ParseException {
        this.endDate = format.parse(end);
    }

    @Override
    public String toString() {
        return (taskName + " - " + taskDescription + "\n" + "start: " + startDate + " end: " + endDate);
    }
}
