package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @Getter
    @Setter
    @NotNull
    String id = UUID.randomUUID().toString();
}
