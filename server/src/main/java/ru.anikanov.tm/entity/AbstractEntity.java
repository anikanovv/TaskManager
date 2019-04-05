package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity {
    @Getter
    @Setter
    @NotNull
    @Id
    String id = UUID.randomUUID().toString();
}
