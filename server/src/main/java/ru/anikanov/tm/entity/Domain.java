package ru.anikanov.tm.entity;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JacksonXmlRootElement
@XmlRootElement(name = "Domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class Domain extends AbstractEntity implements Serializable {
    @Nullable
    @XmlElement
    private List<Project> projects;
    @Nullable
    @XmlElement
    private List<Task> tasks;
}
