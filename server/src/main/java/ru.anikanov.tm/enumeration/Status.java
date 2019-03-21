package ru.anikanov.tm.enumeration;

import org.jetbrains.annotations.NotNull;

public enum Status {
    SCHEDULED("scheduled"),
    INPROCESS("in the process"),
    DONE("done");

    @NotNull
    private final String name;


    Status(@NotNull String name) {
        this.name = name;
    }

    public String displayName() {
        return name;
    }
}
