package com.revature.planetarium.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import org.sqlite.SQLiteConfig;

public class DatabaseConnector {

    public static Connection getConnection() throws SQLException {
        //SQLiteConfig config = new SQLiteConfig();
        String user = "postgres1";
        String password = "trng2010rds1";
        //config.enforceForeignKeys(true);
        //String url = System.getenv("PLANETARIUM");
        String url = "jdbc:postgresql://trng2010-rds-1.chueiwozbnfz.us-east-1.rds.amazonaws.com:5432/postgres";
        //return DriverManager.getConnection(url, config.toProperties());
        return DriverManager.getConnection(url, user, password);
    }

}
