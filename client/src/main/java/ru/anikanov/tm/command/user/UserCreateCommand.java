package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Role;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.utils.PasswordHash;

public class UserCreateCommand extends AbstractCommand {

    @Override
    public String getName() {
        return "register user";
    }

    @Override
    public String getDescription() {
        return "create new user";
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public void execute() {
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        System.out.println("Name,pass,role");
        final String userName = bootstrap.getTerminalService().nextLine();
        final String firstName = bootstrap.getTerminalService().nextLine();
        final String lastName = bootstrap.getTerminalService().nextLine();
        final String email = bootstrap.getTerminalService().nextLine();
        final String userPass = bootstrap.getTerminalService().nextLine();
        final String userRole = bootstrap.getTerminalService().nextLine();
        Role role = null;
        if (userRole != null) {
            if (userRole.equals("admin")) role = Role.ADMIN;
            else role = Role.USER;
        }
        if (userPass != null) {
            endPoint.createUser(userName, firstName, lastName, email, PasswordHash.makehash(userPass), role);
        }
    }
}
