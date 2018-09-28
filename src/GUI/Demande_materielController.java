/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import GUI.Accueil_clientController;
import tn.MedicaSud.app.client.gui.Utilites;
import tn.MedicaSud.entities.Demande;
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.TypeMateriel;
import tn.MedicaSud.services.DemandeServicesRemote;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.text.Utilities;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Demande_materielController implements Initializable {
	 @FXML
	    private ImageView imgConsulterDemande;
	  @FXML
	    private JFXButton  ConsulterDemande ;
	@FXML
    private ImageView imgAccceuil;
    @FXML
    private JFXButton EnvoyerDemandeMateriel;
    @FXML
    private JFXButton ConsulterMateriel;
    @FXML
    private JFXButton NouveauTicket;
    @FXML
    private JFXButton DemandeMateriel;
    @FXML
    private JFXButton Deconnexion;
    @FXML
    private JFXButton EditerProfile;
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton consulterTicket;
    @FXML
    private ImageView imgConsulterTicket;
    @FXML
    private ImageView ImgNouveauTicket;
    @FXML
    private ImageView ImgDemandeMateriel;
    @FXML
    private ImageView imgConsulterMateriel;
    @FXML
    private ImageView ImageDeconnexion;
    @FXML
    private ImageView ImgEditerProfile;
    @FXML 
    private JFXTextArea description;
    @FXML
    private JFXComboBox status;
    @FXML
    private JFXComboBox typeMateriel;
    private ObservableList<String> etatData=FXCollections.observableArrayList();
    private ObservableList<String> typeMterielData=FXCollections.observableArrayList();
    Utilites utilites= new Utilites();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         utilites.backgroundImage(imageMedicaSud);
         Image img = new Image("Assets/icons8-voir-les-détails-50.png");
    	   imgConsulterTicket.setImage(img);
    	   
    	    img = new Image("Assets/icons8-poste-de-travail-64.png");
  	   imgConsulterMateriel.setImage(img);
  	   
  	   img = new Image("Assets/icons8-ajouter-32.png");
  	   ImgNouveauTicket.setImage(img);
  	   
  	   img = new Image("Assets/Demande.png");
  	   ImgDemandeMateriel.setImage(img);
  	   
  	   img = new Image("Assets/icons8-editer-le-fichier-80.png");
  	   ImgEditerProfile.setImage(img);
  	   
  	   img = new Image("Assets/icons8-connexion-filled-50.png");
  	   ImageDeconnexion.setImage(img);
  	   
  	   imgAccceuil.setImage(img);
  	   TypeMateriel[] data=TypeMateriel.values();
  	   for (TypeMateriel typeMateriel : data) {
		typeMterielData.add(String.valueOf(typeMateriel));
	}
  	   typeMateriel.setItems(typeMterielData);
  	   
  	   StatutTicket[] data2=StatutTicket.values();
  	   for (StatutTicket statutTicket : data2) {
		etatData.add(String.valueOf(statutTicket));
	}
  	   status.setItems(etatData);
    }    



    @FXML
    private void ConsulterMaterielAction(ActionEvent event) throws IOException {
        utilites.newStage(Deconnexion, "Consulter_matériel.fxml", "consulter matériels");
    }
    @FXML
    private void NouveauTicketAction(ActionEvent event) throws IOException {
          utilites.newStage(Deconnexion, "Ajouter_tickets.fxml"," nouveau ticket");
    }
    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException { 
        utilites.newStage(Deconnexion, "login.fxml","login");
        Accueil_clientController.utilisateurConnecte=null;

    }
    @FXML
    private void EditerProfileAction(ActionEvent event) throws IOException {
        utilites.newStageWithOldStage("EditerMotDePasse.fxml");
       
    }
    @FXML
    private void consulterTicketAction(ActionEvent event) throws IOException {
                 utilites.newStage(Deconnexion, "Consulter_ticket.fxml","consulter tickets");

    }
    @FXML
    private void DemandeMaterielAction(ActionEvent event) throws IOException {
               utilites.newStage(Deconnexion, "Demande_materiel.fxml","demande matériel");
    }
    
    @FXML
    private void EnvoyerDemandeMaterielAction(ActionEvent event) throws IOException, NamingException {
    	String msg="";
    	if (typeMateriel.getValue()==null) {
    		msg="type materiel non désigné!";
    	    utilites.GenererAlerte(msg);
    	}

    	else if (description.getText().equals(null)) {
    		msg=" demande non remplie !";
    	    utilites.GenererAlerte(msg);
    	}
    	else if (status.getValue()==null) {
			msg="status demande non désigné!";
		   utilites.GenererAlerte(msg);
    	}
    	
    
    else
    {
    	Demande demande= new Demande();
    	demande.setDateDemande(Date.valueOf(java.time.LocalDate.now()));
    	demande.setDescription(description.getText());
    	demande.setTypeMateriel(TypeMateriel.valueOf((String) typeMateriel.getValue()));
    	demande.setStatus(StatutTicket.valueOf((String) status.getValue()));
        demande.setUtilisateur(Accueil_clientController.utilisateurConnecte);
        utilites.context= new InitialContext();
        utilites.demandeServicesRemote=(DemandeServicesRemote) utilites.context.lookup(utilites.demandeRemote);
        utilites.demandeServicesRemote.save(demande);
    	utilites.GenerertAletrtOk("Envoie effectué");
    	 utilites.newStage(Deconnexion, "demande_materiel.fxml", "demande matériel");
    	
    }
    }
    @FXML
    private void retourAcceuil() throws IOException
    {
    	   utilites.newStage(Deconnexion, "Accueil_client.fxml","consulter tickets");
    	
    }
    @FXML
    private void ConsulterDemandeMaterielAction(ActionEvent event) throws IOException {
                  utilites.newStage(Deconnexion, "ConsulterDemandeMateriel.fxml", "login");

    }
}
