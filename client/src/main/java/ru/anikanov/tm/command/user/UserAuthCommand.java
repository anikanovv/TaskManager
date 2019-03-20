package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.User;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.utils.PasswordHash;

public class UserAuthCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return true;
    }
    @Override
    public String getName() {
        return "auth user";
    }

    @Override
    public String getDescription() {
        return "authorization user";
    }

    @Override
    public void execute() {
        final UserEndPoint endPoint=bootstrap.getUserEndPoint();
        System.out.println("login");
        final String login = bootstrap.getTerminalService().nextLine();
        System.out.println("pass");
        final String pass = PasswordHash.makehash(bootstrap.getTerminalService().nextLine());
        if (endPoint.logIn(login, pass)) {
            User user = endPoint.findOneUser(login, login);
            bootstrap.setCurrentUser(user.getName());
            System.out.println("AUTH OK!");
            System.out.println(user.getRole());
        }
    }
}
