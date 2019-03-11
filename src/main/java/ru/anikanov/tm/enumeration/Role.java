package ru.anikanov.tm.enumeration;

public enum Role {
    USER("User"),
    ADMIN("Admin");

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
