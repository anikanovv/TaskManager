package ru.anikanov.tm;


import ru.anikanov.tm.bootstrap.Bootstrap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

@ApplicationScoped
public class AppServer {

    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        initializer.addPackages(AppServer.class);
        SeContainer container = initializer.initialize();
        container.select(Bootstrap.class).get().init();

    }
 /*   public static void main(String[] args) throws Exception {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        initializer.addPackages(AppServer.class);
        SeContainer container = initializer.initialize();
        UserDto project=container.select(Bootstrap.class).get().getUserEndPoint().logIn("user","user");
//        User project = bootstrap.userService.findOne("e742c623-e978-4b2b-8439-fd1ec41cebf7");
        //        Session session=bootstrap.sessionService.findOne("bb290dc7-4c51-4128-98f1-70a374bfd921");
//        List<User>users=bootstrap.userService.findAll();
//        bootstrap.userService.merge("Cheburek", "Kek", "Looman", "Looman@man.com", PasswordHashUtil.md5("user"), Role.USER,user.getId());
        System.out.println("11");*/
//    }
}