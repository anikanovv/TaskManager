package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;
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
        final IUserService userService = bootstrap.getUserService();
        System.out.println("login");
        final String login = bootstrap.getTerminalService().nextLine();
        System.out.println("pass");
        final String pass = PasswordHash.makehash(bootstrap.getTerminalService().nextLine());
        if (userService.logIn(login, pass)) {
            User user = userService.findOne(login, login);
            bootstrap.setCurrentUser(user.getName());
            System.out.println("AUTH OK!");
            System.out.println(user.getRole());
        }
    }
}
