/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.EtatTicket;
import Entities.Intervention;
import Entities.Materiel;
import Entities.Utilisateur;
import static GUI.ConsulterMaterielParUtilisateurController.utilisateur;
import Services.InterventionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.naming.NamingException;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConsulterInterventionParMaterielController implements Initializable {

    @FXML
    private TableView<Intervention> listeDemandes;
    @FXML
    private TableColumn<Intervention, String> identifiantIntervention;
    @FXML
    private TableColumn<Intervention, String> desriptionIntervention;
    @FXML
    private TableColumn<Intervention, Materiel> materielIntervention;
    @FXML
    private TableColumn<Intervention, EtatTicket> etatIntervention;
    
      private ObservableList<Intervention> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void RemplirTableTout(Materiel m) throws NamingException, SQLException
    {	
    	List<Intervention> interventions= new ArrayList<Intervention>();
    	List<Intervention> materiels1= new ArrayList<Intervention>();
        InterventionServices is= new InterventionServices();
        materiels1=is.AfficherInterventionParMateriel(m);
	 data=FXCollections.observableList(materiels1);		
  	 identifiantIntervention.setCellValueFactory(new PropertyValueFactory<>("id"));
		 	  desriptionIntervention.setCellValueFactory(new PropertyValueFactory<>("description"));
		 	  etatIntervention.setCellValueFactory(new PropertyValueFactory<>("etatIntervention"));
		 	 materielIntervention.setCellValueFactory(new PropertyValueFactory<>("materiel"));
		 	  listeDemandes.setItems(data);
		 	   
    
    }
    
}
