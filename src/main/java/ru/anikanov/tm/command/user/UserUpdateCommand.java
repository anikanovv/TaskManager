package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.enumeration.Role;


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
        IUserService userService = bootstrap.getUserService();
        String login = scanner.next();
        String pass = bootstrap.passwordHash(scanner.next());
        String newRole = scanner.next();
        Role role;
        if (newRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        userService.merge(login, pass, role);
    }
}
