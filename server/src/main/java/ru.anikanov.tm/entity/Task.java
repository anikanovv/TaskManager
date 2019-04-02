package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateFormatUtil;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Task extends AbstractEntity {
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
//    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.US);

    public Task(@Nullable final String projectId, @Nullable final String name, @Nullable final String description,
                @Nullable final String startDate, @Nullable final String endDate, @Nullable final String userId) throws Exception {
        this.projectId = projectId;
        this.taskName = name;
        this.taskDescription = description;
        setStart(startDate);
        setEnd(endDate);
        this.userId = userId;
        status = Status.SCHEDULED;
    }

    public String getStart() {
        return new DateFormatUtil().dateToString(startDate);
    }

    public void setStart(@Nullable final String start) {
        try {
            startDate = new java.sql.Date(new DateFormatUtil().stringToDate(start).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getEnd() {
        return new DateFormatUtil().dateToString(startDate);
    }

    public void setEnd(@Nullable final String end) {
        try {
            endDate = new java.sql.Date(new DateFormatUtil().stringToDate(end).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return (taskName + " - " + taskDescription + "\n" + "start: " + startDate + " end: " + endDate);
    }
}
