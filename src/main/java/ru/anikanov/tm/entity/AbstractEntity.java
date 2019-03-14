package ru.anikanov.tm.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public abstract class AbstractEntity {
    @Getter
    @Setter
    String id = UUID.randomUUID().toString();
    private String name;
}
