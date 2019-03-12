package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.UserServiceInterface;

public class UserDeleteCommand extends AbstractCommand {

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
        UserServiceInterface userService = bootstrap.getUserService();
        String login = scanner.nextLine();
        userService.remove(login, bootstrap.getCurrentUser());
    }
}
