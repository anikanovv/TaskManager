package ru.anikanov.tm;

import ru.anikanov.tm.bootstrap.Bootstrap;

import java.sql.SQLException;


public class AppServer {
    public static void main(String[] args) throws SQLException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.init();
    }
}