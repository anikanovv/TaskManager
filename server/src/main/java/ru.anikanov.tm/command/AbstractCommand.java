package ru.anikanov.tm.command;

import lombok.Setter;
import ru.anikanov.tm.api.ServiceLocator;

public abstract class AbstractCommand {
    @Setter
    public ServiceLocator bootstrap;

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isSecure();

    public abstract void execute() throws Exception;
}
