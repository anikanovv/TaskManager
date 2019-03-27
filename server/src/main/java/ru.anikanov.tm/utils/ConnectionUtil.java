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
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/test1";
    private static final String USER = "sa";
    private static final String PASS = "";

    /*  public static Connection getConnection() {
          Connection conn = null;
          try {
              Class.forName(JDBC_DRIVER);
              System.out.println("Connecting to database...");
              conn = DriverManager.getConnection(DB_URL, USER, PASS);
              conn.close();
          } catch (Exception se) {
              se.printStackTrace();
          }
          return conn;
      }*/
    @Nullable
    public static Connection getConnection() {
        Connection conn = null;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
