package ru.anikanov.tm.command.system;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.command.AbstractCommand;

public class ExitCommand extends AbstractCommand {
    protected ExitCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {

    }
}
