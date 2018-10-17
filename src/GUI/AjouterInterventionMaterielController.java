/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import Entities.Intervention;
import Entities.Materiel;
import Services.InterventionServices;
import Services.MaterielService;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterInterventionMaterielController implements Initializable {

    @FXML
    private Label idLabel;
    @FXML
    private Label labelPeriode;
    @FXML
    private JFXTextField IdentifiantIntervention;
    @FXML
    private JFXTextField DescriptionIntervention;
    @FXML
    private DatePicker DateIntervention;
    @FXML
    private JFXTextField PeriodeIntervention;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private Hyperlink externe;
    @FXML
    private Label labelEtat;
    @FXML
    private JFXComboBox<?> etatIntervention;
    @FXML
    private Label labelMateriel;
    @FXML
    private JFXComboBox MaterielIntervention;
    Utilites utilities= new Utilites();
    ObservableList<String> dataMateriels=FXCollections.observableArrayList();
    InterventionServices is= new InterventionServices();
    MaterielService ms= new MaterielService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       etatIntervention.setVisible(false);
       labelEtat.setVisible(false);
       
  	try {
	   	List<Materiel> materiels= ms.displayAll();
	   			
	    	 for (Materiel materiel1 : materiels) {
	     		 System.out.println(materiel1.getReference());
	   		dataMateriels.add(materiel1.getReference());
	     		 //dataMateriels.
	   	}
	     	 MaterielIntervention.setItems(dataMateriels);
        } catch (SQLException ex) {
            Logger.getLogger(AjouterInterventionMaterielController.class.getName()).log(Level.SEVERE, null, ex);
        }
   	
    }    

    @FXML
    private void EnregistrerIntervetnion(ActionEvent event) throws NamingException, SQLException    {
    	String mat=(String) MaterielIntervention.getValue();
    	Integer i=0;
    	for (String string : dataMateriels) {
			if(string!=mat)			{
				i++;
			}
		}
	   	List<Materiel> materiels= ms.displayAll();
    	
    	Materiel materiel=materiels.get(i);
    	Intervention intervention= new Intervention();
    	intervention.setId(IdentifiantIntervention.getText());
    	intervention.setDateIntervention(Date.valueOf(DateIntervention.getValue()));
    	intervention.setDescription(DescriptionIntervention.getText());
    	intervention.setMateriel(materiel.getId());
    	intervention.setPeriode(Integer.valueOf(PeriodeIntervention.getText()));
    	is.modifierIntervention(intervention);
    	utilities.closeStage(Enregistrer);
    	utilities.GenerertAletrtOk("intervention enregistrée avec succés");
    	}
    	
    	
    
    
}
