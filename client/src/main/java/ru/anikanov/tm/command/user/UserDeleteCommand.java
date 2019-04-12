package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.UserEndPoint;

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
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        final String login = bootstrap.getTerminalService().nextLine();
        endPoint.removeUser(bootstrap.getCurrentSession(), login);
    }
}
