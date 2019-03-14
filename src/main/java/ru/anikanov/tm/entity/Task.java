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
    private Date start;
    @Nullable
    private Date end;
    @NotNull
    private String userId;
    @NotNull
    private Status status;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Task(@NotNull String projectId, @Nullable String name, @Nullable String description, @Nullable String startDate, @Nullable String endDate, @NotNull String userId) throws Exception {
        this.projectId = projectId;
        this.taskName = name;
        this.taskDescription = description;
        start = format.parse(startDate);
        end = format.parse(endDate);
        this.userId = userId;
        status = Status.SCHEDULED;
    }

    public String getStartDate() {
        return format.format(start);
    }

    public void setStart(String start) throws ParseException {
        this.start = format.parse(start);
    }

    public String getEnd() {
        return format.format(end);
    }

    public void setEnd(String end) throws ParseException {
        this.end = format.parse(end);
    }

    @Override
    public String toString() {
        return (taskName + " - " + taskDescription + "\n" + "start: " + start + " end: " + end);
    }
}
