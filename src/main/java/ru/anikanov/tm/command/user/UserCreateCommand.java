package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.enumeration.Role;

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
        IUserService userService = bootstrap.getUserService();
        System.out.println("Name,pass,role");
        String userName = scanner.nextLine();
        String userPass = scanner.nextLine();
        String userRole = scanner.nextLine();
        Role role;
        if (userRole.equals("admin")) role = Role.ADMIN;
        else role = Role.USER;
        userService.persist(userName, bootstrap.passwordHash(userPass), role);
    }
}
