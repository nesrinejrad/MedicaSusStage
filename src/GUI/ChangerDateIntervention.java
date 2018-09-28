/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import tn.MedicaSud.entities.Intervention;
import tn.MedicaSud.services.InterventionServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ChangerDateIntervention implements Initializable {

    @FXML
    private JFXButton Valider;
    @FXML
    private DatePicker dayeIntervrntion;
    static Intervention intervention= new Intervention();
    Utilites utilities= new Utilites();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       System.out.println(intervention.toString());
    }    

    @FXML
    private void ValiderAction(ActionEvent event) throws NamingException {
    	 System.out.println(intervention.toString());
    	intervention.setDateIntervention(dayeIntervrntion.getValue());
    	utilities.context= new InitialContext();
    	utilities.interventionServicesRemote= (InterventionServicesRemote) Utilites.context.lookup(Utilites.interventionRemote);
    	utilities.interventionServicesRemote.update(intervention);
    	utilities.closeStage(Valider);
    	utilities.GenerertAletrtOk("intervention mise à jour");
    }
    
}
