package ru.anikanov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.service.IProjectService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.Project;

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
        final IProjectService projectService = bootstrap.getProjectService();
        @Nullable final List<Project> projects = projectService.sortedByStartDate(bootstrap.getCurrentUser());
        projects.forEach(project -> System.out.println(project));
    }
}
