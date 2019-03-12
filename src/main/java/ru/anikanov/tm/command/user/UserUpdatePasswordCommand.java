package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.UserServiceInterface;

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
        UserServiceInterface userService = bootstrap.getUserService();
        String login = scanner.nextLine();
        String oldPass = bootstrap.passwordHash(scanner.nextLine());
        String newPass = bootstrap.passwordHash(scanner.nextLine());
        if (userService.updatePassword(login, oldPass, newPass)) System.out.println("updated");
    }
}
