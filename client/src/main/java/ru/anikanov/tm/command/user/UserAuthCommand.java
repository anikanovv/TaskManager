package ru.anikanov.tm.command.user;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.endpoint.Session;
import ru.anikanov.tm.endpoint.UserDto;
import ru.anikanov.tm.endpoint.UserEndPoint;

import java.util.Objects;

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
    public void execute() throws Exception {
        final UserEndPoint endPoint = bootstrap.getUserEndPoint();
        System.out.println("login");
        final String login = bootstrap.getTerminalService().nextLine();
        System.out.println("pass");
        final String pass = bootstrap.getTerminalService().nextLine();
        UserDto userDto = endPoint.logIn(login, pass);
        if (userDto != null) {
            UserDto user = endPoint.findOneUserByName(login);
            System.out.println("AUTH OK!");
            System.out.println(user.getRole());
            Session session = bootstrap.getSessionEndPoint().createSession(user.getId());
            bootstrap.setCurrentSession(session);
            System.out.println(Objects.requireNonNull(bootstrap.getCurrentSession()).getSignature());
            if (bootstrap.getUserEndPoint().checkadmin(bootstrap.getCurrentSession())) System.out.println("ADMIN!!!");
        }
    }
}
