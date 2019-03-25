package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.User;
import ru.anikanov.tm.endpoint.UserEndPoint;

import java.util.List;

public class UserReadAllCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "readall user";
    }

    @Override
    public String getDescription() {
        return "read info about all users";
    }

    @Override
    public void execute() {
        final UserEndPoint endPoint=bootstrap.getUserEndPoint();
        final List<User> users = endPoint.findAllUser(bootstrap.getCurrentSession());
        users.forEach(System.out::println);
    }
}
