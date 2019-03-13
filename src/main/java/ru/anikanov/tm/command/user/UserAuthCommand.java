package ru.anikanov.tm.command.user;


import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;
import ru.anikanov.tm.utils.PasswordHash;

public class UserAuthCommand extends AbstractCommand {

    public UserAuthCommand(ServiceLocator serviceLocator) {
        super(serviceLocator);
    }

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
        final String login = bootstrap.getTerminlService().nextLine();
        System.out.println("pass");
        final String pass = PasswordHash.makehash(bootstrap.getTerminlService().nextLine());
        if (userService.logIn(login, pass)) {
            User user = userService.findOne(login, login);
            bootstrap.setCurrentUser(user.getLogin());
            System.out.println("AUTH OK!");
            System.out.println(user.getRole());
        }
    }
}
