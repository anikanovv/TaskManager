package ru.anikanov.tm;


import ru.anikanov.tm.bootstrap.Bootstrap;

import javax.enterprise.inject.se.SeContainerInitializer;

public class AppServer {
    public static void main(String[] args) throws Exception {
        SeContainerInitializer.newInstance().initialize()
//                .addPackages(AppServer.class).initialize()
                .select(Bootstrap.class).get().init();
    }
/*    public static void main(String[] args) throws Exception {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        initializer.addPackages(AppServer.class);
        SeContainer container = initializer.initialize();
        Project project=container.select(Bootstrap.class).get().getProjectService().findOne("3f1b3211-cfa0-45ab-93a7-eb6c10882854","e742c623-e978-4b2b-8439-fd1ec41cebf7");
//        User project = bootstrap.userService.findOne("e742c623-e978-4b2b-8439-fd1ec41cebf7");
        //        Session session=bootstrap.sessionService.findOne("bb290dc7-4c51-4128-98f1-70a374bfd921");
//        List<User>users=bootstrap.userService.findAll();
//        bootstrap.userService.merge("Cheburek", "Kek", "Looman", "Looman@man.com", PasswordHashUtil.md5("user"), Role.USER,user.getId());
        System.out.println("11");
    }*/
}