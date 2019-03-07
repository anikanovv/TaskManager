package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectService;
import ru.anikanov.tm.service.TaskService;

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
    public void execute(String name) throws ParseException {
        System.out.println(bootstrap.taskService.findOne(name));
    }
}
