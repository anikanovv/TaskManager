package ru.anikanov.tm.entity;

import java.util.UUID;

public class AbstractEntity {
    String id = UUID.randomUUID().toString();
    private String name;
}
