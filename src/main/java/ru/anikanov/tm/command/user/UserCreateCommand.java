package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.enumeration.Role;
import ru.anikanov.tm.utils.PasswordHash;

public class UserCreateCommand extends AbstractCommand {

    public UserCreateCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final IUserService userService = bootstrap.getUserService();
        System.out.println("Name,pass,role");
        final String userName = bootstrap.getTerminlService().nextLine();
        final String userPass = bootstrap.getTerminlService().nextLine();
        final String userRole = bootstrap.getTerminlService().nextLine();
        Role role;
        if (userRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        userService.persist(userName, PasswordHash.makehash(userPass), role);
    }
}
