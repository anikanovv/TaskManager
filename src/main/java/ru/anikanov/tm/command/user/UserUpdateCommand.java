package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.utils.PasswordHash;


public class UserUpdateCommand extends AbstractCommand {

    public UserUpdateCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final String login = bootstrap.getTerminlService().nextLine();
        final String pass = PasswordHash.makehash(bootstrap.getTerminlService().nextLine());
        final String newRole = bootstrap.getTerminlService().nextLine();
        Role role;
        if (newRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        userService.merge(login, pass, role);
    }
}
