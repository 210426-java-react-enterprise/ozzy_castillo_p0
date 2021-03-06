package com.revature.assigments.p0.util;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class that establish the connections between my app and the Data Baser Server >> in this app I'm using Postgres
 */

public class ConnectionFactory {
    private static ConnectionFactory connectionFactory;//I'm using the singleton pattern
    private Properties props = new Properties();

    static {
        try{
            Class.forName("org.postgresql.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    // I'm using a Lazy Singleton
    private ConnectionFactory(){
        try{
            props.load(new FileReader("src/main/resources/application.properties"));
        }catch (IOException e) {
        e.printStackTrace();
    }
    }

    public static ConnectionFactory getInstance(){
        if (connectionFactory == null){
            connectionFactory = new ConnectionFactory();
        }

        return connectionFactory;
    }

    public Connection getConnection(){
        Connection conn=null;

        try{

            conn = DriverManager.getConnection(
                    props.getProperty("host-url"),
                    props.getProperty("username"),
                    props.getProperty("password"));

        }catch(SQLException e){
            e.printStackTrace();
        }

        return conn;

    }

}
