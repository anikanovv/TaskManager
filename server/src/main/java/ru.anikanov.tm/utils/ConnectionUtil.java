package ru.anikanov.tm.utils;

import org.jetbrains.annotations.Nullable;
import ru.anikanov.tm.bootstrap.Bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    @Nullable
    public static Connection getConnection() {
        Connection conn = null;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String filename = "config.properties";
            input = Bootstrap.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return null;
            }
            prop.load(input);
            String host = prop.getProperty("db.host");
            String login = prop.getProperty("db.login");
            String password = prop.getProperty("db.password");
            System.out.println("HOST: " + host
                    + ", LOGIN: " + login
                    + ", PASSWORD: " + password);
            try {
                conn =
                        DriverManager.getConnection(host, login, password);
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
        return conn;
    }

}
