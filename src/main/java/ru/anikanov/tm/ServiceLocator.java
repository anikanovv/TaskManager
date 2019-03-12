package ru.anikanov.tm;

import ru.anikanov.tm.command.AbstractCommand;
import ru.anikanov.tm.service.ProjectServiceInterface;
import ru.anikanov.tm.service.TaskServiceInterface;
import ru.anikanov.tm.service.UserServiceInterface;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Map;
import java.util.Scanner;

public interface ServiceLocator {
    Scanner getScanner();

    String getCurrentUser();

    void setCurrentUser(String currentUser);

    ProjectServiceInterface getProjectService();

    TaskServiceInterface getTaskService();

    UserServiceInterface getUserService();

    Map<String, AbstractCommand> initCommands();

    String passwordHash(String string);

    void init() throws ParseException, UnsupportedEncodingException, NoSuchAlgorithmException;
}
