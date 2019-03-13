package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

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
        IProjectService projectService = bootstrap.getProjectService();
        String name = scanner.nextLine();
        System.out.println(projectService.findOne(name, bootstrap.getCurrentUser()));
    }
}
