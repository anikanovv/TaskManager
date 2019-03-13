package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;

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
        IUserService userService = bootstrap.getUserService();
        String login = scanner.nextLine();
        String oldPass = bootstrap.passwordHash(scanner.nextLine());
        String newPass = bootstrap.passwordHash(scanner.nextLine());
        if (userService.updatePassword(login, oldPass, newPass)) System.out.println("updated");
    }
}
