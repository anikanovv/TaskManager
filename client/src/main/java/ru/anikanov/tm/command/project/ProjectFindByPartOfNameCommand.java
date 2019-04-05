package ru.anikanov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.ProjectDto;

public class ProjectFindByPartOfNameCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "findbypart project";
    }

    @Override
    public String getDescription() {
        return "find project by part of name";
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("type name(part of name)");
        @Nullable final String partOfName = bootstrap.getTerminalService().nextLine();
        @Nullable ProjectDto project = bootstrap.getProjectEndPoint().findProjectByPartOfNameProject(bootstrap.getCurrentSession(), partOfName);
        System.out.println(project);
    }
}
