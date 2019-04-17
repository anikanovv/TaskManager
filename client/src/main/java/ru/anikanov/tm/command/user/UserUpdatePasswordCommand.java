package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.UserEndPoint;
import ru.anikanov.tm.utils.PasswordHash;

import java.util.Objects;

public class UserUpdatePasswordCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getName() {
        return "updatepass user";
    }

    @Override
    public String getDescription() {
        return "to update user password";
    }

    @Override
    public void execute() {
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        final String login = bootstrap.getTerminalService().nextLine();
        final String oldPass = PasswordHash.makehash(Objects.requireNonNull(bootstrap.getTerminalService().nextLine()));
        final String newPass = PasswordHash.makehash(Objects.requireNonNull(bootstrap.getTerminalService().nextLine()));
//        if (endPoint.updateUserPassword(login, oldPass, newPass)) System.out.println("updated");
    }
}
