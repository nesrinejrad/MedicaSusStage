/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import GUI.Accueil_clientController;
import tn.MedicaSud.app.client.gui.Utilites;
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Panne;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Ticket;
import tn.MedicaSud.services.MaterielServicesRemote;
import tn.MedicaSud.services.PanneServicesRemote;
import tn.MedicaSud.services.TicketSerciesRemote;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;

import Utilities.SendMail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Ajouter_ticketsController implements Initializable {
	 @FXML
	    private ImageView imgConsulterDemande;
	  @FXML
	    private JFXButton  ConsulterDemande ;
	
    @FXML
    private ImageView imgAccceuil;
    Utilites utilites= new Utilites();
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton consulterTicket;
    @FXML
    private JFXButton ConsulterMateriel1;
    @FXML
    private JFXButton NouveauTicket1;
    @FXML
    private JFXButton DemandeMateriel;
    @FXML
    private JFXButton EditerProfile1;
    
    @FXML
     private JFXButton Deconnexion;
    @FXML
    private Hyperlink AutresPAnnes1;
    @FXML
    private JFXButton ValiderNouveauTicket1;
    @FXML
    private ImageView imgConsulterTicket;
    @FXML
    private ImageView ImgNouveauTicket;
    @FXML
    private ImageView ImgDemandeMateriel;
    @FXML
    private ImageView imgConsulterMateriel;
    @FXML
    private ImageView ImgEditerProfile;
    @FXML
    private ImageView ImageDeconnexion;
    @FXML
    private JFXComboBox<String> materiel;
    @FXML
    private JFXComboBox<String> Panne;
    @FXML
    private JFXComboBox<String> statut;
    @FXML
    private JFXTextArea description;
     ObservableList<String> dataMateriels=FXCollections.observableArrayList();
     ObservableList<String> dataPannes=FXCollections.observableArrayList();;
    private ObservableList<String> dataStatutTickets=FXCollections.observableArrayList();

	
	



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
  	   
  	 List<Materiel> materiels= Accueil_clientController.utilisateurConnecte.getMateriels();
  	System.out.println(Accueil_clientController.utilisateurConnecte.getCode());
  	 System.out.println("size materiel"+materiels.size());
  	 for (Materiel materiel1 : materiels) {
  		 System.out.println(materiel1.getReference());
		dataMateriels.add(materiel1.getReference());
  		 //dataMateriels.
	}
  	 materiel.setItems(dataMateriels);
  	 
  	 try {
		utilites.context= new InitialContext();
		utilites.panneServicesRemote=(PanneServicesRemote) utilites.context.lookup(utilites.panneRemote);
		List<Panne> pannes= new ArrayList<Panne>();
		pannes=utilites.panneServicesRemote.findAll();
		System.out.println("panne size="+pannes.size());
		for (Panne panne1 : pannes) {
				dataPannes.add(panne1.getDescription());
		}
		Panne.setItems(dataPannes);
	  } 
  	 catch (NamingException e) {
	  }
  	 
  	 StatutTicket[] statusTickets=StatutTicket.values();
  	 for (StatutTicket statutTicket : statusTickets) {
		dataStatutTickets.add(String.valueOf(statutTicket));
	}
     statut.setItems(dataStatutTickets);
    }    


    @FXML
    private void AutresPAnnesAction(ActionEvent event) throws IOException {
    	Nouvelle_PannesController nouvelle_PannesController= new Nouvelle_PannesController();
    	nouvelle_PannesController.dim1= (int) Deconnexion.getScene().getWindow().getWidth();
    	nouvelle_PannesController.dim2= (int) Deconnexion.getScene().getWindow().getHeight();    	
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Nouvelle_Pannes.fxml"));
        Parent root = (Parent) loader.load();
    	nouvelle_PannesController=loader.getController();
    	nouvelle_PannesController.NouvellePanneUser();		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
  	
    }

  
    @FXML
    private void ConsulterMaterielAction(ActionEvent event) throws IOException {
              utilites.newStage(Deconnexion, "Consulter_matériel.fxml", " consulter matériels");

    }
    @FXML
    private void NouveauTicketAction(ActionEvent event) throws IOException { 
    	
        utilites.newStage(Deconnexion, "Ajouter_tickets.fxml"," nouveau ticket");
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
          utilites.newStage(Deconnexion, "login.fxml", "login");
          Accueil_clientController.utilisateurConnecte=null;

    }
    @FXML
    private void EditerProfileAction(ActionEvent event) throws IOException {
          utilites.newStageWithOldStage( "EditerMotDePasse.fxml");
    }
    @FXML
    private void consulterTicketAction(ActionEvent event) throws IOException {
         utilites.newStage(Deconnexion, "Consulter_ticket.fxml","consulter tickets");
    }
    @FXML
    private void DemandeMaterielAction(ActionEvent event) throws IOException {
            utilites.newStage(Deconnexion, "Demande_materiel.fxml", "demande matériel");
    }
    @FXML
    private void retourAcceuil() throws IOException
    {
    	   utilites.newStage(Deconnexion, "Accueil_client.fxml","consulter tickets");
    	
    }
    
    @FXML
    private void ValiderNouveauTicketAction(ActionEvent event) throws IOException, NamingException {
     	String msg="";
    	if (statut.getValue()==null) {
    		msg="statut non désigné!";
    	    utilites.GenererAlerte(msg);
    	}

    	else if (description.getText().equals(null)) {
    		msg=" description non remplie !";
    	    utilites.GenererAlerte(msg);
    	}
    	else if (materiel.getValue()==null) {
			msg="materiel non désigné!";
		   utilites.GenererAlerte(msg);
    	}
    	else if (description.getText().equals(null)) {
    		msg=" description non remplie !";
    	    utilites.GenererAlerte(msg);
    	}
    	else if (Panne.getValue()==null) {
			msg="panne non désigné!";
		   utilites.GenererAlerte(msg);
    	}
    	
    else
    {
    	utilites.context=new InitialContext();
    	utilites.ticketSerciesRemote=(TicketSerciesRemote) utilites.context.lookup(utilites.ticketRemote);
    	Ticket ticket= new Ticket();
    	ticket.setDiscription(description.getText());
    	ticket.setStatutTicket(StatutTicket.valueOf(statut.getValue()));
    	String mat=materiel.getValue();
    	Integer i=0;
    	for (String string : dataMateriels) {
			if(string!=mat)			{
				i++;
			}
		}
    	List<Materiel> materiels= new ArrayList<Materiel>();
    	materiels= Accueil_clientController.utilisateurConnecte.getMateriels();
    	Materiel materiel=materiels.get(i);
    	ticket.setMateriel(materiel);
    	ticket.setTypeMateriel(materiel.getTypeMateriel());
    	ticket.setUtilisateur(Accueil_clientController.utilisateurConnecte);
    	ticket.setEtatTicket(EtatTicket.valueOf("nonTraité"));
    	Panne panne= new Panne();
    	utilites.panneServicesRemote=(PanneServicesRemote) utilites.context.lookup(utilites.panneRemote);
    	List<Panne> pannes=utilites.panneServicesRemote.findAll();
    	i=0;
    	mat=Panne.getValue();
    	for (String string : dataPannes) {
			if(mat!=string)
			{
				i++;
			}
		}
    	panne=pannes.get(i);
    	//ticket.setPanne(panne);
    	utilites.ticketSerciesRemote.save(ticket);
    	SendMail sm =new SendMail();
		  sm.sendmail("jrad.nesrine1@gmail.com","nesrine.jrad@esprit.tn", "07188923", "your login to helpDesk platfrom \n Login:"+" \n Password :");
    	utilites.GenerertAletrtOk("Envoie effectué");
        utilites.newStage(Deconnexion, "Ajout_tickets.fxml", "demande matériel");
    }
    }
    @FXML
    private void ConsulterDemandeMaterielAction(ActionEvent event) throws IOException {
                  utilites.newStage(Deconnexion, "ConsulterDemandeMateriel.fxml", "login");

    }
}
