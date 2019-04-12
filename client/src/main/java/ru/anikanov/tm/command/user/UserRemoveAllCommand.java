package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.UserEndPoint;

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
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        endPoint.removeAllUser(bootstrap.getCurrentSession());
    }
}
