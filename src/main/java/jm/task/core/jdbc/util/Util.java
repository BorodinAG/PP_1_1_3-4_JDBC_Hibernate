package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/first_base?serverTimezone=Europe/Moscow&useSSL=false";
        String name = "user";
        String password = "123";
        Connection connection;
        try {
            connection = DriverManager.getConnection(url, name, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
