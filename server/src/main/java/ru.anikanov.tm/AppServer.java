package ru.anikanov.tm;

import ru.anikanov.tm.bootstrap.Bootstrap;


public class AppServer {
    public static void main(String[] args) throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}