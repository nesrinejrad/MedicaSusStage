/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Utiles.MyBdConnexion;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class TestConnexion {
    static Connection connection;
    public static void main(String[] args) throws SQLException {
        connection= MyBdConnexion.getInstanceBD().getConnection();
        if(connection!=null)
        {
            System.out.println("Tests.TestConnexion.main()");
        }
        
    }
    
}
