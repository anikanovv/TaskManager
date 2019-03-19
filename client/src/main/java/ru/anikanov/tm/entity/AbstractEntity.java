package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class AbstractEntity {
    @Getter
    @Setter
    @NotNull
    String id = UUID.randomUUID().toString();
    private String name;
}
