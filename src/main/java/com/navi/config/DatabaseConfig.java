package com.navi.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private String url = "jdbc:postgresql://localhost:5432/system";
    private String user = "postgres";
    private String password = "postgres";

    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexão com PostgreSQL realizada.");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

}
