package ru.anikanov.tm.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateFormatUtil;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Project extends AbstractEntity implements Serializable {
    @NotNull
    @Id
    private String id = super.getId();
    @Nullable
    private String name;
    @Nullable
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

    public Project(@Nullable final String name, @Nullable final String description, @Nullable final String startDate,
                   @Nullable final String endDate, @Nullable final String userId) {
        this.name=name;
        this.description = description;
        setStart(startDate);
        setEnd(endDate);
        this.userId = userId;
    }

    @XmlTransient
    public String getEnd() {
        return new DateFormatUtil().dateToString(endDate);
    }

    @XmlTransient
    public String getStart() {
        return new DateFormatUtil().dateToString(startDate);
    }

    public void setStart(@Nullable final String startString) {
        try {
            startDate = new java.sql.Date(new DateFormatUtil().stringToDate(startString).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEnd(@Nullable final String endString) {
        try {
            endDate = new java.sql.Date(new DateFormatUtil().stringToDate(endString).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ("Project " + name + " - " + description + " " + id + "\n" + "Start: " + (startDate) + " end: " + (endDate) + "\n" + "Tasks :\n");
    }
}
