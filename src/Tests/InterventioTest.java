/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Entities.EtatTicket;
import Entities.Intervention;
import Entities.Materiel;
import Services.InterventionServices;
import Services.MaterielService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class InterventioTest {
    public static void main(String[] args) throws SQLException {
        Intervention intervention= new Intervention();
        
        intervention.setId("id");
        intervention.setDescription("description");
        intervention.setDateIntervention(Date.valueOf("2018-10-06"));
        intervention.setEtatIntervention(EtatTicket.nonTraité);
        intervention.setPeriode(6);
        intervention.setPeriode(5);
        InterventionServices is= new InterventionServices();
        //is.ajouterIntervention(intervention);
//        LocalDate date = intervention.getDateIntervention().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    /*    intervention.getDateIntervention().toLocalDate();
        System.out.println(intervention.getDateIntervention());
        List<Intervention> interventions= new ArrayList<>();
                interventions=is.displayAll();
                System.out.println(interventions.size());
                for (int i = 0; i < interventions.size(); i++) {
            Intervention get = interventions.get(i);
                    System.out.println(get.getPeriode());
                    MaterielService ms=new MaterielService();
                    Materiel m=ms.rechercherMateriel("njkk");
                    is.AfficherInterventionParMateriel(m);*/
    
        //}
        MaterielService ms= new MaterielService();
        Materiel m = ms.rechercherMateriel("njkk");
        List<Intervention> ml=is.AfficherInterventionParMateriel(m);
        System.out.println(ml.size());
        
    }
}
