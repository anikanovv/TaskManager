package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.UserServiceInterface;

public class UserRemoveAllCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "removeall users";
    }

    @Override
    public String getDescription() {
        return "to remove all users";
    }

    @Override
    public void execute() {
        UserServiceInterface userService = bootstrap.getUserService();
        userService.removeAll(bootstrap.getCurrentUser());
    }
}
