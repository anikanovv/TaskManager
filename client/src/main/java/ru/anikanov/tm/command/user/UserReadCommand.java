package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.UserDto;
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
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        final String login = bootstrap.getTerminalService().nextLine();
        final UserDto user = endPoint.findOneUser(bootstrap.getCurrentSession(), login);
        System.out.println(user);
    }
}
