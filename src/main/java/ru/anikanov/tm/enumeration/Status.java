package ru.anikanov.tm.enumeration;

public enum Status {
    SCHEDULED("scheduled"),
    INPROCESS("in the process"),
    DONE("done");

    private final String name;


    Status(String name) {
        this.name = name;
    }

    public String displayName() {
        return name;
    }
}
