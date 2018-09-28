/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Intervention;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Panne;
import tn.MedicaSud.services.InterventionServicesRemote;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ListeInterventionsController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton ListeTickets;
    @FXML
    private JFXButton ListeInterventions;
    @FXML
    private JFXButton Accueil;
    @FXML
    private JFXButton Deconnexion;
 
    @FXML
    private TableView<Intervention> interventionTable;
    @FXML
    private TableColumn<Intervention, String> identifiantIntervention;
    @FXML
    private TableColumn<Intervention, String> desriptionIntervention;
    @FXML
    private TableColumn<Intervention, Materiel> materielIntervention;
    @FXML
    private TableColumn<Intervention, EtatTicket> etatIntervention;
    @FXML
    private JFXButton interventionResolu;
    @FXML
    private JFXButton changerDate;
    Utilites utilities= new Utilites();
    @FXML
    private JFXButton consulterTicket;
    private ObservableList<Intervention> data;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilities.backgroundImage(imageMedicaSud);
    	List<Intervention> interventions= new ArrayList<Intervention>();
    	try {
			utilities.context= new InitialContext();
			utilities.interventionServicesRemote=(InterventionServicesRemote) utilities.context.lookup(utilities.interventionRemote);
			interventions=utilities.interventionServicesRemote.findAll();
			List<Intervention> interventions2= new ArrayList<Intervention>();
			System.out.println(java.time.LocalDate.now());

			for (Intervention intervention : interventions) {
				System.out.println(intervention.getDateIntervention());

				if(intervention.getDateIntervention().equals(java.time.LocalDate.now()))
				{
					interventions2.add(intervention);
				}
			}
			  data=FXCollections.observableList(interventions2);		
		 	  identifiantIntervention.setCellValueFactory(new PropertyValueFactory<>("id"));
		 	  desriptionIntervention.setCellValueFactory(new PropertyValueFactory<>("description"));
		 	  etatIntervention.setCellValueFactory(new PropertyValueFactory<>("etatIntervention"));
		 	 materielIntervention.setCellValueFactory(new PropertyValueFactory<>("materiel"));
		 	  interventionTable.setItems(data);
		 	   
    	} catch (NamingException e) {
		
		}

    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilities.newStageWithOldStage("AjouterInterventionMateriel.fxml");
 }

    @FXML
    private void ListeTicketsAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "ConsulterTicketAdmin.fxml", "Liste des tickets");
}

    @FXML
    private void ListeInterventionsAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "ListeInterventions.fxml", "liste des interventions");   }


    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "AccueilAdmin.fxml", "Accueil");
}

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
      	Intervention intervention= interventionTable.getSelectionModel().getSelectedItem();
    	ChangerDateIntervention changerDateIntervention= new ChangerDateIntervention();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ChangerDateIntervention.fxml"));
        Parent root = (Parent) loader.load();
    	changerDateIntervention=loader.getController();
    	changerDateIntervention.intervention=intervention;	
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);}

    @FXML
    private void interventionResoluAction(ActionEvent event) throws NamingException {
    	Intervention intervention= new Intervention();
    	intervention=interventionTable.getSelectionModel().getSelectedItem();
    	intervention.setEtatIntervention(EtatTicket.valueOf("résolu"));
    	utilities.context= new InitialContext();
    	utilities.interventionServicesRemote=(InterventionServicesRemote) utilities.context.lookup(utilities.interventionRemote);
    	utilities.interventionServicesRemote.update(intervention);
    	utilities.GenerertAletrtOk("intervention mise à jour");
    }

    @FXML
    private void changerDateAction(ActionEvent event) throws IOException {
      	Intervention intervention= interventionTable.getSelectionModel().getSelectedItem();
    	ChangerDateIntervention changerDateIntervention= new ChangerDateIntervention();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ChangerDateIntervention.fxml"));
        Parent root = (Parent) loader.load();
    	changerDateIntervention=loader.getController();
    	changerDateIntervention.intervention=intervention;
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);
    }
    @FXML
    private void consulterTicketAction(ActionEvent event) {
    	
    }
}
