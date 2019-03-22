package ru.anikanov.tm.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement
@XmlRootElement(name = "Project")
@XmlAccessorType(XmlAccessType.FIELD)
public class Project extends AbstractEntity implements Serializable {
    @JacksonXmlElementWrapper(useWrapping = false)
    @Nullable
    @XmlElement
    private String name;
    @Nullable
    @XmlElement
    private String description;
    @Nullable
    @XmlElement
    private Date startDate;
    @Nullable
    @XmlElement
    private Date endDate;
    @Nullable
    @XmlElement
    private String userId;
    @Nullable
    @XmlElement
    private Status status;

    public Project(@Nullable final String name, @Nullable final String description, @Nullable final String startDate,
                   @Nullable final String endDate, @Nullable final String userId) {
        this.name=name;
        this.description = description;
        setStart(startDate);
        setEnd(endDate);
        this.userId = userId;
        status = Status.SCHEDULED;
    }

    @XmlTransient
    public String getEnd() {
        return new DateToString().dateToString(endDate);
    }

    @XmlTransient
    public String getStart() {
        return new DateToString().dateToString(startDate);
    }

    public void setStart(@Nullable final String startString) {
        try {
            startDate = new DateToString().stringToDate(startString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEnd(@Nullable final String endString) {
        try {
            endDate = new DateToString().stringToDate(endString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return ("Project " + name + " - " + description + " " + id + "\n" + "Start: " + (startDate) + " end: " + (endDate) + "\n" + "Tasks :\n");
    }
}
