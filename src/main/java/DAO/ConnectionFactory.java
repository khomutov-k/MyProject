package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection createConnection() throws SQLException {
        final String url = "jdbc:mysql://localhost:3306/hoteldb";
        final String user = "root";
        final String password = "root";
        return DriverManager.getConnection(url, user, password);
    }
}
