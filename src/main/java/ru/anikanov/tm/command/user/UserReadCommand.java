package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.service.UserServiceInterface;

public class UserReadCommand extends AbstractCommand {

    @Override
    public boolean isSecure() {
        return false;
    }
    @Override
    public String getName() {
        return "read user";
    }

    @Override
    public String getDescription() {
        return "read info about user";
    }

    @Override
    public void execute() {
        UserServiceInterface userService = bootstrap.getUserService();
        String login = scanner.nextLine();
        User user = userService.findOne(login, bootstrap.getCurrentUser());
        System.out.println(user);
    }
}
