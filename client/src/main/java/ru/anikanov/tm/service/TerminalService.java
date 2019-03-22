package ru.anikanov.tm.service;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.ITerminalService;
import ru.anikanov.tm.command.AbstractCommand;

import java.util.Objects;
import java.util.Scanner;

public class TerminalService implements ITerminalService {
    @Getter
    @NotNull
    private final Scanner scanner = new Scanner(System.in);
    @NotNull
    private final ServiceLocator serviceLocator;

    public TerminalService(@NotNull ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void terminalCicle() {
        while (true) {
            @Nullable String commandString;
            System.out.println("command");
                commandString = scanner.nextLine().trim();
                @Nullable AbstractCommand command = serviceLocator.getCommandMap().get(commandString);
                if (command != null)
                    if ((command.isSecure()) || (!Objects.requireNonNull(serviceLocator.getCurrentUser()).isEmpty())) {
                        try {
                            command.execute();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else System.out.println("wrong");
                else System.out.println("wrong command");
        }
    }

    @Nullable
    public String nextLine() {
        return scanner.nextLine();
    }
}
