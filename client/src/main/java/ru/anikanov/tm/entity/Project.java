package ru.anikanov.tm.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.enumeration.Status;
import ru.anikanov.tm.utils.DateToString;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
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
    @NotNull
    @XmlElement
    private Date startDate;
    @Nullable
    @XmlElement
    private Date endDate;
    @NotNull
    @XmlElement
    private String userId;
    @NotNull
    @XmlElement
    private Status status;

    public Project() {

    }

    public Project(String name, String description, String startDate, String endDate, String userId) throws Exception {
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

    public void setStart(String startString) throws Exception {
        startDate = new DateToString().stringToDate(startString);
    }

    public void setEnd(String endString) throws Exception {
        endDate = new DateToString().stringToDate(endString);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Project " + name + " - " + description + " " + id + "\n" + "Start: " + (startDate) + " end: " + (endDate) + "\n" + "Tasks :\n");
        return builder.toString();
    }
}
