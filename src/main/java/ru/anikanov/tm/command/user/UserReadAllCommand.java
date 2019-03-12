package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.service.UserServiceInterface;
import java.util.List;

public class UserReadAllCommand extends AbstractCommand {

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
        UserServiceInterface userService = bootstrap.getUserService();
        List<User> users = userService.findAll(bootstrap.getCurrentUser());
        users.forEach(user -> System.out.println(user));
    }
}
