package ru.anikanov.tm;

import ru.anikanov.tm.bootstrap.Bootstrap;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.system.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.command.project.*;


public class App {
    private static final Class[] CLASSES = {HelpCommand.class, ExitCommand.class, AboutCommand.class, ProjectCreateCommand.class, ProjectDeleteCommand.class, ProjectReadCommand.class,
            ProjectRemoveAllCommand.class, ProjectUpdateCommand.class, TaskCreateCommand.class, TaskDeleteCommand.class, TaskReadCommand.class,
            TaskRemoveAllCommand.class, TaskUpdateCommand.class, UserAuthCommand.class, UserCreateCommand.class, UserDeleteCommand.class,
            UserEndSessionCommand.class, UserRemoveAllCommand.class, UserReadCommand.class, UserRemoveAllCommand.class, UserUpdateCommand.class,
            UserUpdatePasswordCommand.class};
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init(CLASSES);
    }
}
