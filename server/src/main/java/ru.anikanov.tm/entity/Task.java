package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Getter
@Setter
@NoArgsConstructor
public final class Task extends AbstractEntity {
    @Nullable
    private String projectId;
    @Nullable
    private String taskName;
    @Nullable
    private String taskDescription;
    @Nullable
    private Date startDate;
    @Nullable
    private Date endDate;
    @Nullable
    private String userId;
    @Nullable
    private Status status;
    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Task(@Nullable final String projectId, @Nullable final String name, @Nullable final String description,
                @Nullable final String startDate, @Nullable final String endDate, @Nullable final String userId) throws Exception {
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

    public void setStart(@Nullable final String start) {
        try {
            this.startDate = format.parse(start);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEnd() {
        return format.format(startDate);
    }

    public void setEnd(@Nullable final String end) {
        try {
            this.endDate = format.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return (taskName + " - " + taskDescription + "\n" + "start: " + startDate + " end: " + endDate);
    }
}
