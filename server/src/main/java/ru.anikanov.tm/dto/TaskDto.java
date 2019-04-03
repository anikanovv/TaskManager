package ru.anikanov.tm.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.anikanov.tm.enumeration.Status;

import java.util.Date;

@NoArgsConstructor
@Setter
@Getter
public class TaskDto {
    private String id;
    private String name;
    private String description;
    private Date dateBegin;
    private Date dateEnd;
    private Status status;
}
