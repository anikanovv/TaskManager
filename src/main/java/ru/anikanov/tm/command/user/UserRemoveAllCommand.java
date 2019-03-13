package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;

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
        IUserService userService = bootstrap.getUserService();
        userService.removeAll(bootstrap.getCurrentUser());
    }
}
