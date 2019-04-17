package ru.anikanov.tm;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.anikanov.tm.api.ServiceLocator;
import ru.anikanov.tm.utils.SpringConfig;

@Component
public class AppServer {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        ServiceLocator serviceLocator = applicationContext.getBean(ServiceLocator.class);
        serviceLocator.init();
    }
}