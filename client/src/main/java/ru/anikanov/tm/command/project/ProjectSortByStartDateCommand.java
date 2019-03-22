package ru.anikanov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Project;
import ru.anikanov.tm.endpoint.ProjectEndPoint;

import java.util.List;

public class ProjectSortByStartDateCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "sortbystartdate project";
    }

    @Override
    public String getDescription() {
        return "return projects, sorted by start date";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        final ProjectEndPoint projectEndPoint= bootstrap.getProjectEndPoint();
        @Nullable final List<Project> projects = projectEndPoint.sortProjectByStartDate(bootstrap.getCurrentUser());
        if (projects != null) {
            projects.forEach(System.out::println);
        }
    }
}
