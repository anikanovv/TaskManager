package ru.anikanov.tm.command.user;

import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;

import java.util.List;

public class UserReadAllCommand extends AbstractCommand {

    public UserReadAllCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "readall user";
    }

    @Override
    public String getDescription() {
        return "read info about all users";
    }

    @Override
    public void execute() {
        final IUserService userService = bootstrap.getUserService();
        final List<User> users = userService.findAll(bootstrap.getCurrentUser());
        users.forEach(user -> System.out.println(user));
    }
}
