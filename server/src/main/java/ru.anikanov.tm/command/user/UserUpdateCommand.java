package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.utils.PasswordHash;


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
        final IUserService userService = bootstrap.getUserService();
        final String login = bootstrap.getTerminalService().nextLine();
        final String pass = PasswordHash.makehash(bootstrap.getTerminalService().nextLine());
        final String newRole = bootstrap.getTerminalService().nextLine();
        Role role;
        if (newRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        userService.merge(login, pass, role);
    }
}
