package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;

public class UserDeleteCommand extends AbstractCommand {

    public UserDeleteCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "delete user";
    }

    @Override
    public String getDescription() {
        return "delete one user";
    }

    @Override
    public void execute() {
        final IUserService userService = bootstrap.getUserService();
        final String login = bootstrap.getTerminlService().nextLine();
        userService.remove(login, bootstrap.getCurrentUser());
    }
}
