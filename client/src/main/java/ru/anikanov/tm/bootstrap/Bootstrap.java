package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@ApplicationScoped

public class Bootstrap implements ServiceLocator {
    @Nullable
    private String currentUser;
    @Nullable
    private Session currentSession;
    @Inject
    private ProjectEndPoint projectEndPoint;
    @Inject
    private TaskEndPoint taskEndPoint;
    @Inject
    private UserEndPoint userEndPoint;
    @Inject
    private SessionEndPoint sessionEndPoint;
    @Inject
    private DomainEndPoint domainEndPoint;
    @Inject
    private ITerminalService terminalService;
    @NotNull
    private Map<String, AbstractCommand> commandMap = new HashMap<>();


    public void init(@NotNull final Class[] classes) {
        try {
            initCommands(classes);
            while (true) {
                @Nullable String commandString;
                System.out.println("command");
                commandString = getTerminalService().nextLine().trim();
                @Nullable AbstractCommand command = getCommandMap().get(commandString);
                if (command != null)
                    if ((command.isSecure()) || (!Objects.requireNonNull(Objects.requireNonNull(getCurrentSession()).getUserId()).isEmpty())) {
                        try {
                            command.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else System.out.println("wrong");
                else System.out.println("wrong command");
            }
        } catch (Exception ignored) {
        }

    }

    private void initCommands(@NotNull final Class[] classes) throws Exception {
        for (@Nullable Class c : classes) {
            if ((c != null) && (c.getSuperclass().equals(AbstractCommand.class))) {
                @NotNull AbstractCommand command = (AbstractCommand) c.newInstance();
                command.setBootstrap(this);
                commandMap.put(command.getName(), command);
            }
        }
    }

}
