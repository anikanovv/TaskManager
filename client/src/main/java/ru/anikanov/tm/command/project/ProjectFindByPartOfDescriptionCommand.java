package ru.anikanov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Project;
import ru.anikanov.tm.endpoint.ProjectEndPoint;

public class ProjectFindByPartOfDescriptionCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findbydescription project";
    }

    @Override
    public String getDescription() {
        return "find project by part of description";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final ProjectEndPoint projectEndPoint= bootstrap.getProjectEndPoint();
        System.out.println("type description(part of description)");
        @Nullable final String partOfDescription = bootstrap.getTerminalService().nextLine();
        @Nullable Project project = projectEndPoint.findProjectByPartOfNameProject(partOfDescription, bootstrap.getCurrentUser());
        System.out.println(project);
    }
}
