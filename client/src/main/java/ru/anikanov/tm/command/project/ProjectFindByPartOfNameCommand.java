package ru.anikanov.tm.command.project;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Project;

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
        @Nullable Project project = bootstrap.getProjectEndPoint().findProjectByPartOfNameProject(partOfName, bootstrap.getCurrentSession());
        System.out.println(project);
    }
}
