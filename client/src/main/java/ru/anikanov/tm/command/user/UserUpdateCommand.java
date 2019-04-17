package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Role;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.utils.PasswordHash;

import java.util.Objects;


public class UserUpdateCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getName() {
        return "update user";
    }

    @Override
    public String getDescription() {
        return "updating user";
    }

    @Override
    public void execute() {
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        final String login = bootstrap.getTerminalService().nextLine();
        final String firstName = bootstrap.getTerminalService().nextLine();
        final String lastName = bootstrap.getTerminalService().nextLine();
        final String email = bootstrap.getTerminalService().nextLine();
        final String pass = PasswordHash.makehash(Objects.requireNonNull(bootstrap.getTerminalService().nextLine()));
        final String newRole = bootstrap.getTerminalService().nextLine();
        final String id = bootstrap.getTerminalService().nextLine();
        Role role = null;
        if (newRole != null) {
            if (newRole.equals("admin")) role = Role.ADMIN;
            else role = Role.USER;
        }
        endPoint.updateUser(login, firstName, lastName, email, pass, role, id);
    }
}
