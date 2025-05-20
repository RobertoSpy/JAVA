package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static DatabaseConnectionManager instance;
    private Connection connection;

    private DatabaseConnectionManager() {
        try {
            
            Class.forName("oracle.jdbc.OracleDriver");
           
            String url = "jdbc:oracle:thin:@localhost:1521:xe";  
            String user = "LAB8";  
            String password = "LAB8";  
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
