package ru.anikanov.tm.service;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.ITermincalService;
import ru.anikanov.tm.command.AbstractCommand;

import java.util.Scanner;

public class TerminalService implements ITermincalService {
    private final Scanner scanner = new Scanner(System.in);
    private final ServiceLocator serviceLocator;

    public TerminalService(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    public void terminalCicle() throws Exception {
        do {
            String commandString;
            System.out.println("command");
            commandString = scanner.nextLine().trim();
            AbstractCommand command = serviceLocator.getCommandMap().get(commandString);
            if (command != null)
                if ((command.isSecure()) || (!serviceLocator.getCurrentUser().isEmpty())) command.execute();
                else System.out.println("wrong");
        } while (true);
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
