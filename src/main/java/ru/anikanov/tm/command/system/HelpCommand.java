package ru.anikanov.tm.command.system;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.command.AbstractCommand;

public class HelpCommand extends AbstractCommand {

    public HelpCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescription() {
        return "information about all commands";
    }

    @Override
    public boolean isSecure() {
        return true;
    }
    @Override
    public void execute() {
        bootstrap.getCommandMap().forEach((k, v) -> System.out.println(v.getName() + " - " + v.getDescription()));
    }
}
