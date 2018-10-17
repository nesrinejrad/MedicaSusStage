/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.Materiel;
import Services.MaterielService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Material;

/**
 *
 * @author USER
 */
public class MaterielTest {
    public static void main(String[] args) throws SQLException {
     MaterielService ms= new MaterielService();
        List<Materiel> maListe= ms.displayAllMaterielParUtilisateur("code1");
        System.out.println(maListe.size());
        for (int i = 0; i <maListe.size(); i++) {
            Materiel get = maListe.get(i);
            System.out.println(get.getId());
            
        }
    
    /*   Materiel m = new Materiel();
       m.setId("id");
       m.setDureeGarantie(50);
       m.setMarque("mars");
       ms.ajouterMateriel(m);*/
       
    }
    
}
