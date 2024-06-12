package org.sistema.reserva.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    //db name
    private static final String users = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/fase2";

    private static Connection conn;


    public static Connection getConnection(){

        try{
            if(conn == null){
                conn = DriverManager.getConnection(url, users, password);
            }
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}
