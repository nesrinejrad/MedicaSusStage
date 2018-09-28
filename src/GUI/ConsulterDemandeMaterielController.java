/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import GUI.Accueil_clientController;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import tn.MedicaSud.entities.Demande;
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Ticket;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.DemandeServicesRemote;
import tn.MedicaSud.services.TicketSerciesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConsulterDemandeMaterielController implements Initializable {

    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton Accueil;
    @FXML
    private ImageView imgAccceuil;
    @FXML
    private JFXButton consulterTicket;
    @FXML
    private ImageView imgConsulterTicket;
    @FXML
    private JFXButton ConsulterMateriel;
    @FXML
    private JFXButton NouveauTicket;
    @FXML
    private ImageView ImgNouveauTicket;
    @FXML
    private JFXButton DemandeMateriel;
    @FXML
    private ImageView ImgDemandeMateriel;
    @FXML
    private ImageView imgConsulterMateriel;
    @FXML
    private JFXButton ConsulterDemande;
    @FXML
    private ImageView ImgConsulterDemande;
    @FXML
    private JFXButton EditerProfile;
    @FXML
    private ImageView ImgEditerProfile;
    @FXML
    private JFXButton Deconnexion;
    @FXML
    private ImageView ImageDeconnexion;
    @FXML
    private TableView<Demande> listeDemandes;
    @FXML
    private TableColumn<Demande, String> type;
    @FXML
    private TableColumn<Demande, String> description;
    @FXML
    private TableColumn<Demande, Date> date;
    @FXML
    private TableColumn<Demande, Utilisateur> utilisateur;
    @FXML
    private TableColumn<Demande,StatutTicket > status;
    Utilites utilies= new Utilites();
    private ObservableList<Demande> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    	  utilies.backgroundImage(imageMedicaSud);
          
     	   Image img = new Image("Assets/icons8-voir-les-détails-50.png");
     	   imgConsulterTicket.setImage(img);
     	   
     	    img = new Image("Assets/icons8-poste-de-travail-64.png");
   	   imgConsulterMateriel.setImage(img);
   	   
   	   img = new Image("Assets/icons8-ajouter-32.png");
   	   ImgNouveauTicket.setImage(img);
   	   
   	   img = new Image("Assets/demandeblanche.png");
   	   ImgDemandeMateriel.setImage(img);;
   	   
   	   img = new Image("Assets/icons8-editer-le-fichier-80.png");
   	   ImgEditerProfile.setImage(img);
   	   
   	   img = new Image("Assets/icons8-connexion-filled-50.png");
   	   ImageDeconnexion.setImage(img);

   	   
   	   imgAccceuil.setImage(img);
    }    

    @FXML
    private void retourAcceuil(ActionEvent event) throws IOException {
 	   utilies.newStage(Deconnexion, "Accueil_client.fxml","consulter tickets");

    }

    @FXML
    private void consulterTicketAction(ActionEvent event) throws IOException {
        utilies.newStage(Deconnexion, "Consulter_ticket.fxml","consulter tickets");

    }

    @FXML
    private void ConsulterMaterielAction(ActionEvent event) throws IOException {
        utilies.newStage(Deconnexion, "Consulter_matériel.fxml", "consulter matéreiels");

    }

    @FXML
    private void NouveauTicketAction(ActionEvent event) throws IOException {            
    utilies.newStage(Deconnexion, "Ajouter_tickets.fxml","nouveau ticket");

    }

    @FXML
    private void DemandeMaterielAction(ActionEvent event) throws IOException {
        utilies.newStage(Deconnexion, "Demande_materiel.fxml","demander matériel");

    }

    @FXML
    private void ConsulterDemandeMaterielAction(ActionEvent event) throws IOException {
        utilies.newStage(Deconnexion, "ConsulterDemandeMateriel.fxml", "login");
      	
    }

    @FXML
    private void EditerProfileAction(ActionEvent event) throws IOException {
         utilies.newStageWithOldStage("EditerMotDePasse.fxml");

        
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
    	utilies.newStage(Deconnexion, "login.fxml", "login");
    	Accueil_clientController.utilisateurConnecte=null;
    }
    
    public void remplirDemandeUtilisateur()
    {try {
		utilies.context = new InitialContext();
		utilies.demandeServicesRemote=(DemandeServicesRemote) utilies.context.lookup(utilies.demandeRemote);
		List<Demande> demandes=utilies.demandeServicesRemote.findAll();
		List<Demande> demandesUtilisateur= new ArrayList<Demande>();
		for (Demande demande : demandes) {
			if(demande.getUtilisateur().getCode().equals(Accueil_clientController.utilisateurConnecte.getCode()))
			{	System.out.println("*********");
				demandesUtilisateur.add(demande);
			}
		}
		data=FXCollections.observableList(demandesUtilisateur);
	} catch (NamingException e) {
		
	}
	  type.setCellValueFactory(new PropertyValueFactory<>("type"));
 	  description.setCellValueFactory(new PropertyValueFactory<>("description"));
 	  date.setCellValueFactory(new PropertyValueFactory<>("date"));
 	  status.setCellValueFactory(new PropertyValueFactory<>("status"));
 	 utilisateur.setVisible(false);
 	 this.listeDemandes.setItems(data);
    	
    }
    
    public void remplirDemandeAdmin()
    {try {
		utilies.context = new InitialContext();
		utilies.demandeServicesRemote=(DemandeServicesRemote) utilies.context.lookup(utilies.demandeRemote);
		List<Demande> demandes=utilies.demandeServicesRemote.findAll();
		data=FXCollections.observableList(demandes);
	} catch (NamingException e) {
		
	}
	  type.setCellValueFactory(new PropertyValueFactory<>("type"));
 	  description.setCellValueFactory(new PropertyValueFactory<>("description"));
 	  date.setCellValueFactory(new PropertyValueFactory<>("date"));
 	  status.setCellValueFactory(new PropertyValueFactory<>("status"));
 	 utilisateur.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
 	 this.listeDemandes.setItems(data);
    	
    }
    
    
}
