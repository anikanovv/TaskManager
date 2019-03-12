package ru.anikanov.tm.command.system;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;

public class HelpCommand extends AbstractCommand {

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
        bootstrap.initCommands().forEach((k, v) -> System.out.println(v.getName() + " - " + v.getDescription()));
    }
}
