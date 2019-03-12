package ru.anikanov.tm.command.user;


import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.UserServiceInterface;

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
        UserServiceInterface userService = bootstrap.getUserService();
        System.out.println("login");
        String login = scanner.nextLine();
        System.out.println("pass");
        String pass = bootstrap.passwordHash(scanner.nextLine());
        if (userService.auth(login, pass)) {
            bootstrap.setCurrentUser(userService.findOne(login, login).getLogin());
            System.out.println("AUTH OK!");
            System.out.println(userService.findOne(login, login).getRole());
        }
    }
}
