package ru.anikanov.tm.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.*;
import ru.anikanov.tm.service.TerminalService;

import java.lang.Exception;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Bootstrap implements ServiceLocator {

    @Nullable
    private String currentUser;
    @Nullable
    private Session currentSession;
    @NotNull
    private ProjectEndPoint projectEndPoint=new ProjectEndPointService().getProjectEndPointPort();
    @NotNull
    private TaskEndPoint taskEndPoint=new TaskEndPointService().getTaskEndPointPort();
    @NotNull
    private UserEndPoint userEndPoint=new UserEndPointService().getUserEndPointPort();
    @NotNull
    private SessionEndPoint sessionEndPoint = new SessionEndPointService().getSessionEndPointPort();
    @NotNull
    private DomainEndPoint domainEndPoint = new DomainEndPointService().getDomainEndPointPort();
    @NotNull
    private final ITerminalService terminalService = new TerminalService(this);
    @NotNull
    private Map<String, AbstractCommand> commandMap = new HashMap<>();

    public void init(@NotNull final Class[] classes) throws Exception {
        try {
            initCommands(classes);
            terminalService.terminalCicle();
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
