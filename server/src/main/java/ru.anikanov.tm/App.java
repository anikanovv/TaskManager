package ru.anikanov.tm;

import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.bootstrap.Bootstrap;


public class App {
    @NotNull
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}