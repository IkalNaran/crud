package org.example;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLTimeoutException;

public class CConection {

    String user = "root";
    String pss = "Digital22";
    String bd = "bdescuela";
    String driver = "";
    String ip = "localhost";
    String puerto = "3306";
    String url = "jdbc:mysql://" + ip +":"+ puerto + "/" + bd;
    Connection connection = null;
    public Connection estableConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,pss);
            JOptionPane.showMessageDialog(null, "la conexio se a relizado con exito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, error" + e.toString());
        }
        return connection;
    }
}
