package ru.anikanov.tm;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.bootstrap.Bootstrap;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.system.ExitCommand;
import ru.anikanov.tm.command.system.HelpCommand;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.command.project.*;

public class App {
    @NotNull
    private static final Class[] CLASSES = {AbstractCommand.class, ExitCommand.class, HelpCommand.class,
            ProjectCreateCommand.class, ProjectDeleteCommand.class, ProjectReadCommand.class,
            ProjectRemoveAllCommand.class, ProjectUpdateCommand.class, ProjectFindByPartOfNameCommand.class,
            ProjectFindByPartOfDescriptionCommand.class, ProjectFindAllCommand.class, ProjectSortByEndDateCommand.class,
            ProjectSortByStartDateCommand.class, ProjectSortByStatusCommand.class, TaskCreateCommand.class,
            TaskSortByEndDateCommand.class, TaskSortByStartDateCommand.class, TaskSortByStatusCommand.class, TaskFindByPartOfNameCommand.class,
            TaskDeleteCommand.class, TaskReadCommand.class, TaskRemoveAllCommand.class, TaskUpdateCommand.class,
            TaskFindByPartOfDescriptionCommand.class, UserAuthCommand.class, UserCreateCommand.class,
            UserDeleteCommand.class, UserEndSessionCommand.class, UserRemoveAllCommand.class,
            UserReadCommand.class, UserRemoveAllCommand.class, UserUpdateCommand.class, UserUpdatePasswordCommand.class};

    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASSES);
    }
}
