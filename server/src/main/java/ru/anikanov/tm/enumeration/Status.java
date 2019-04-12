package ru.anikanov.tm.enumeration;

import org.jetbrains.annotations.Nullable;

public enum Status {
    SCHEDULED("scheduled"),
    INPROCESS("in the process"),
    DONE("done");

    @Nullable
    private final String name;

    Status(@Nullable final String name) {
        this.name = name;
    }

    @Nullable
    public String displayName() {
        return name;
    }
}
