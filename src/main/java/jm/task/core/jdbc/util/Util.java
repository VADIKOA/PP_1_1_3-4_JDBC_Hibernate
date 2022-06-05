package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/javapp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    private static Connection connection;

    private Util() {

    }

    public static Connection getConnection() throws SQLException {
        return connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
