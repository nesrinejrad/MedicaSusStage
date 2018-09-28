/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Role;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.MaterielServicesRemote;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffecterUtilisateurMaterielController implements Initializable {

    @FXML
    private TableView<Utilisateur> UtilisateurTableView;
    @FXML
    private TableColumn<Utilisateur, String> codeUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> prenomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> emailUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> fonctionUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> motdepasseUtilisateur;
    @FXML
    private JFXButton Ajouter;
    Materiel materiel= new Materiel();
    @FXML
    private JFXButton enlever;
    @FXML
    private JFXButton Affecter;
    @FXML
    private TableView<Materiel> materiels;
   static Utilisateur utilisateur= new Utilisateur();
    Utilites utilities= new Utilites();
    private ObservableList<Utilisateur> utilisateursData;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	   

    }

    
    public void RemplirTable(Materiel materiel) throws NamingException
    {	
    	List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
    	List<Utilisateur> utilisateurs1= new ArrayList<Utilisateur>();	
    	utilities.context= new InitialContext();
    	this.materiel=materiel;
		utilities.utilisateurServicesRemote=(UtilisateurServicesRemote) utilities.context.lookup(utilities.utilRemote );
    	utilisateurs=utilities.utilisateurServicesRemote.findAll();
  	    for (Utilisateur utilisateur : utilisateurs) {
  	    	System.out.println(utilisateur.getEmail());
  	    	System.out.println("size utili="+utilisateurs.size());
  		 boolean exist=false;
  		List<Materiel> materiels= new ArrayList<Materiel>();
  		  materiels=utilisateur.getMateriels();
  		System.out.println("size mat="+materiels.size());
  		  for (Materiel materiel2 : materiels) {
  			 System.out.println("materiel éli jé="+materiel.getId()); 
 			  System.out.println("materiel lé5ée="+materiel2.getId());
  			  if((materiel.getId().equals(materiel2.getId())))
  			  {  System.out.println("ok");
  			  	exist=true;}}
  			  if(!exist)
  			  {	System.out.println("add");
  				  utilisateurs1.add(utilisateur);
  			  
  	    }	}
  	    	UtilisateurTableView.setItems(null);
	    	utilisateursData=FXCollections.observableList(utilisateurs1);
	   	    codeUtilisateur.setCellValueFactory(new PropertyValueFactory<>("code"));
	   	    nomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("nom"));
	   	    prenomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	   	    motdepasseUtilisateur.setCellValueFactory(new PropertyValueFactory<>("password"));
	   	    emailUtilisateur.setCellValueFactory(new PropertyValueFactory<>("email"));
	   	    fonctionUtilisateur.setCellValueFactory(new PropertyValueFactory<>("fonction"));
	   	    UtilisateurTableView.setItems(utilisateursData);
	   
    
    }
   
  
 
    @FXML
    private void AffecterUtilisateurMateriel() throws NamingException, IOException
    {		 System.out.println("le materiel ici est null="+materiel.getId());
    	Utilisateur utilisateur= new Utilisateur();
    	utilisateur=UtilisateurTableView.getSelectionModel().getSelectedItem();
    	List<Materiel> materiels= new ArrayList<Materiel>();
    	materiels=utilisateur.getMateriels();
    	materiels.add(materiel);
    	utilisateur.setMateriels(materiels);
    	utilities.context= new InitialContext();
    	utilities.utilisateurServicesRemote=(UtilisateurServicesRemote) utilities.context.lookup(utilities.utilRemote);
    	utilities.utilisateurServicesRemote.update(utilisateur);
    	
    	AffecterUtilisateurMaterielController affecterUtilisateurMaterielController= new AffecterUtilisateurMaterielController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AffecterUtilisateurMateriel.fxml"));
        Parent root = (Parent) loader.load();
    	affecterUtilisateurMaterielController=loader.getController();
    	affecterUtilisateurMaterielController.RemplirTable(materiel);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    	
    }
    

}
