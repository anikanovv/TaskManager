package ru.anikanov.tm.command.user;


import ru.anikanov.tm.api.service.IUserService;
import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.entity.User;

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
        IUserService userService = bootstrap.getUserService();
        System.out.println("login");
        String login = scanner.nextLine();
        System.out.println("pass");
        String pass = bootstrap.passwordHash(scanner.nextLine());
        if (userService.logIn(login, pass)) {
            User user = (User) userService.findOne(login, login);
            bootstrap.setCurrentUser(user.getLogin());
            System.out.println("AUTH OK!");
            System.out.println(user.getRole());
        }
    }
}
