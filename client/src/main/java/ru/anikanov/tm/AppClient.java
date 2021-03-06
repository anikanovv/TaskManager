package ru.anikanov.tm;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.command.load.*;
import ru.anikanov.tm.command.project.*;
import ru.anikanov.tm.command.save.*;
import ru.anikanov.tm.command.system.ExitCommand;
import ru.anikanov.tm.command.system.HelpCommand;
import ru.anikanov.tm.command.task.*;
import ru.anikanov.tm.command.user.*;
import ru.anikanov.tm.utils.SpringConfig;

public class AppClient {
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
            UserReadCommand.class, UserRemoveAllCommand.class, UserUpdateCommand.class, UserUpdatePasswordCommand.class,
            SaveFasterJsonCommand.class, SaveFasterXmlCommand.class, SaveJaxBXmlCommand.class, SaveJaxBJsonCommand.class, SaveSerializeCommand.class,
            LoadDeserilizationCommand.class, LoadJaxBXmlCommand.class, LoadJaxBJsonCommand.class,
            LoadFasterJsonCommand.class, LoadFasterXmlCommand.class};

    public static void main(final String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        ServiceLocator serviceLocator = applicationContext.getBean(ServiceLocator.class);
        serviceLocator.init(CLASSES);
    }
}
