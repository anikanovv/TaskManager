package ru.anikanov.tm;

public enum Role {
    USER("user"),
    ADMIN("admin");

    private final String name;

    Role(String role) {
        name = role;
    }

    public String displayName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
