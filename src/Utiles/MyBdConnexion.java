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
    public String url="jdbc:mysql://192.168.1.4/parcinformatiquedb";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static MyBdConnexion instance;
    private  MyBdConnexion() {
            
         try {
             cnx= (Connection) DriverManager.getConnection(url,login,pwd);
         } catch (SQLException ex) {
         
             System.err.println(ex.getMessage());
         }
        
    }
    public static  MyBdConnexion getInstance()
    {
        if(instance==null)
            instance=new  MyBdConnexion();
        return(instance);
    }
    
    public Connection getConnection()
    {
        return(cnx);
    }
    
}
