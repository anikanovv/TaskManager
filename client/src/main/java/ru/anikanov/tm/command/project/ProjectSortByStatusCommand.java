package ru.anikanov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Project;
import ru.anikanov.tm.endpoint.ProjectEndPoint;

import java.util.List;

public class ProjectSortByStatusCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "sortbystatus project";
    }

    @Override
    public String getDescription() {
        return "return projects, sorted by status";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final ProjectEndPoint projectEndPoint= bootstrap.getProjectEndPoint();
        @Nullable final List<Project> projects = projectEndPoint.sortProjectByStatus(bootstrap.getCurrentUser());
        projects.forEach(project -> System.out.println(project));
    }
}
