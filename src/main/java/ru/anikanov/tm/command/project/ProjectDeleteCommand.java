package ru.anikanov.tm.command.project;

import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;

import java.text.ParseException;

public class ProjectDeleteCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "delete project";
    }

    @Override
    public String getDescription() {
        return "command to delete project";
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public void execute() throws ParseException {
        IProjectService projectService = bootstrap.getProjectService();
        String name = scanner.nextLine();
        projectService.remove(name, bootstrap.getCurrentUser());
    }
}
