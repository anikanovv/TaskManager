package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.User;
import ru.anikanov.tm.endpoint.UserEndPoint;

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
        final UserEndPoint endPoint=bootstrap.getUserEndPoint();
        final String login = bootstrap.getTerminalService().nextLine();
        final User user = endPoint.findOneUser(login, bootstrap.getCurrentUser());
        System.out.println(user);
    }
}
