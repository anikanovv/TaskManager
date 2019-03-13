package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.utils.PasswordHash;

public class UserUpdatePasswordCommand extends AbstractCommand {

    protected UserUpdatePasswordCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final IUserService userService = bootstrap.getUserService();
        final String login = bootstrap.getTerminlService().nextLine();
        final String oldPass = PasswordHash.makehash(bootstrap.getTerminlService().nextLine());
        final String newPass = PasswordHash.makehash(bootstrap.getTerminlService().nextLine());
        if (userService.updatePassword(login, oldPass, newPass)) System.out.println("updated");
    }
}
