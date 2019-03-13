package ru.anikanov.tm.command;

import ru.anikanov.tm.api.ServiceLocator;
import java.util.Scanner;

public abstract class AbstractCommand {
    protected final ServiceLocator bootstrap;
//    protected Scanner scanner = bootstrap.getTerminlService().getScanner();

    protected AbstractCommand(ServiceLocator serviceLocator) {
        bootstrap = serviceLocator;
    }

    public abstract String getName();

    public abstract String getDescription();

    public abstract boolean isSecure();

    public abstract void execute() throws Exception;
}
