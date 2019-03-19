package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;

public class UserReadCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "read user";
    }

    @Override
    public String getDescription() {
        return "read info about user";
    }

    @Override
    public void execute() {
        final IUserService userService = bootstrap.getUserService();
        final String login = bootstrap.getTerminalService().nextLine();
        final User user = userService.findOne(login, bootstrap.getCurrentUser());
        System.out.println(user);
    }
}
