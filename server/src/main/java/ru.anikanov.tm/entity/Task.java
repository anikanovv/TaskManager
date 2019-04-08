package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateFormatUtil;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "app_task")
public class Task extends AbstractEntity {

    @Nullable
    @Column(name = "project_id")
    private String projectId;
    @Nullable
    @Column(name = "name")
    private String name;
    @Nullable
    @Column(name = "description")
    private String description;
    @Nullable
    @Column(name = "dateBegin")
    private Date startDate;
    @Nullable
    @Column(name = "dateEnd")
    private Date endDate;
    @Nullable
    @Column(name = "user_id")
    private String userId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Task(@Nullable final String projectId, @Nullable final String name, @Nullable final String description,
                @Nullable final String startDate, @Nullable final String endDate, @Nullable final String userId) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        setStart(startDate);
        setEnd(endDate);
        this.userId = userId;
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
        return (name + " - " + description + "\n" + "start: " + startDate + " end: " + endDate);
    }
}
