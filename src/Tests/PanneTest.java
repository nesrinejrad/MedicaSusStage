/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Panne;
import Entities.TypeMateriel;
import Services.PanneServices;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author USER
 */
public class PanneTest {
    public static void main(String[] args) throws SQLException {
        PanneServices ps= new PanneServices();
        Panne p= new Panne();
        p.setId(4);
        p.setDescription("notdes");
        p.setTypeMateriel(TypeMateriel.téléphone);
        ps.modifierPanne(p);
            
        
    }
    
}
