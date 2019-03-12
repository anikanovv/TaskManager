package ru.anikanov.tm.command.project;

import ru.anikanov.tm.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectServiceInterface;


import java.text.ParseException;

public class ProjectReadCommand extends AbstractCommand {

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
    public void execute() {
        ProjectServiceInterface projectService = bootstrap.getProjectService();
        String name = scanner.nextLine();
        System.out.println(projectService.findOne(name, bootstrap.getCurrentUser()));
    }
}
