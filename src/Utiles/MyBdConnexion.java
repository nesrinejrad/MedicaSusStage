/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class MyBdConnexion {
    String url="jdbc:mysql:"+"//localhost:3306/ParcInformatiquedb";
    String login="root";
    String pwd="";
    Connection connection;
    static MyBdConnexion instanceBD;
    
    private MyBdConnexion() throws SQLException
    {
        connection = DriverManager.getConnection(url,login,pwd);
        System.err.println("connexion établie!");
        
    }
    
    public  static  MyBdConnexion getInstanceBD() throws SQLException
    {
        if(instanceBD==null)
            instanceBD= new MyBdConnexion();
        return instanceBD;
    }
    
    public Connection getConnection()
    {
        return connection;
    }
}
