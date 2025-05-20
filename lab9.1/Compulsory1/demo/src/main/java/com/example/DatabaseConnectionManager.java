package com.example;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("LAB8");
        dataSource.setPassword("LAB8");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");

        
        dataSource.setMinIdle(5);       
        dataSource.setMaxIdle(10);       
        dataSource.setMaxTotal(20);      
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
