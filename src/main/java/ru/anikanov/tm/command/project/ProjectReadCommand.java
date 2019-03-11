package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;


import java.text.ParseException;

public class ProjectReadCommand extends AbstractCommand {
    public ProjectReadCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return "read project";
    }

    @Override
    public String getDescription() {
        return "command to read project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() throws ParseException {
        String name = scanner.next();
        System.out.println(bootstrap.taskService.findOne(name, bootstrap.getCurrentUser()));
    }
}
