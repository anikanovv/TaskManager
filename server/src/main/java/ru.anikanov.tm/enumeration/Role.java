package ru.anikanov.tm.enumeration;

import org.jetbrains.annotations.NotNull;

public enum Role {
    USER("User"),
    ADMIN("Admin");

    @NotNull
    private final String name;

    Role(@NotNull final String role) {
        name = role;
    }

    @NotNull
    public String displayName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
