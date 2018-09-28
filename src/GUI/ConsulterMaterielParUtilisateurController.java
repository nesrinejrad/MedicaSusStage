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
public class ConsulterMaterielParUtilisateurController implements Initializable {
    @FXML
    private TableColumn<Materiel, String> referenceMateriel;
    @FXML
    private TableColumn<Materiel, String> marqueMAteriel;
    @FXML
    private TableColumn<Materiel, LocalDate> DateAchatMAteriel;
    @FXML
    private TableColumn<Materiel, String>CodeMateriel;
    @FXML
    private TableColumn<Materiel, String> DureeGarantie;
    @FXML
    private TableColumn<Materiel, LocalDate> Fournisseur;
    @FXML
    private JFXButton Ajouter;
    private ObservableList<Materiel> data;
    
    @FXML
    private JFXButton enlever;
    @FXML
    private JFXButton Affecter;
    @FXML
    private TableView<Materiel> materiels;
   static Utilisateur utilisateur= new Utilisateur();
    Utilites utilities= new Utilites();
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	   

    }


    @FXML
    private void EnregistrerUtilisateur(ActionEvent event) throws NamingException, IOException {
    
    }
    
    public void RemplirTable(Utilisateur utilisateur)
    {	
    	List<Materiel> materiels= new ArrayList<Materiel>();
  	   materiels.addAll(utilisateur.getMateriels());
  	   data=FXCollections.observableList(materiels);		
  	   System.out.println("data="+data.size());
  	  referenceMateriel.setCellValueFactory(new PropertyValueFactory<>("reference"));
  	  marqueMAteriel.setCellValueFactory(new PropertyValueFactory<>("marque"));
  	  DateAchatMAteriel.setCellValueFactory(new PropertyValueFactory<>("date achat"));
  	  DureeGarantie.setCellValueFactory(new PropertyValueFactory<>("dureeGarantie"));
	  Fournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
	  CodeMateriel.setCellValueFactory(new PropertyValueFactory<>("id"));
  	  this.materiels.setItems(data);
  	enlever.setVisible(true);
	  Ajouter.setVisible(true);
	  Affecter.setVisible(false);
  	  
    
    }
    public void RemplirTableTout() throws NamingException
    {	
    	List<Materiel> materiels= new ArrayList<Materiel>();
    	List<Materiel> materiels1= new ArrayList<Materiel>();
    	List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
    	utilities.context= new InitialContext();
    	utilities.materielServicesRemote= (MaterielServicesRemote) utilities.context.lookup(utilities.materielRemote);
  	   materiels=utilities.materielServicesRemote.findAll();
  	   for (Materiel materiel : materiels) {
  		 boolean exist=false;
  		  utilisateurs=materiel.getUtilisateurs();
  		  for (Utilisateur utilisateur1 : utilisateurs) {
  			  if((utilisateur1.getCode().equals(utilisateur.getCode())))
  			  {  exist=true;	  }}
  		  if(!exist)
  			  materiels1.add(materiel);}
	  	   data=FXCollections.observableList(materiels1);		
  	  referenceMateriel.setCellValueFactory(new PropertyValueFactory<>("reference"));
  	  marqueMAteriel.setCellValueFactory(new PropertyValueFactory<>("marque"));
  	  DateAchatMAteriel.setCellValueFactory(new PropertyValueFactory<>("date achat"));
  	  DureeGarantie.setCellValueFactory(new PropertyValueFactory<>("dureeGarantie"));
	  Fournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
	  CodeMateriel.setCellValueFactory(new PropertyValueFactory<>("id"));
  	  this.materiels.setItems(data);
  	  enlever.setVisible(false);
  	  Ajouter.setVisible(false);
  	  Affecter.setVisible(true);
    
    }
    @FXML
    private void AjouterMaterielUtilisateur() throws NamingException, IOException
    {
    	
    	Stage stage = (Stage) Ajouter.getScene().getWindow();
    	stage.close();
    	ConsulterMaterielParUtilisateurController consulterMaterielParUtilisateurController= new ConsulterMaterielParUtilisateurController();
    	consulterMaterielParUtilisateurController.utilisateur=utilisateur;
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterMaterielParUtilisateur.fxml"));
        Parent root = (Parent) loader.load();
    	consulterMaterielParUtilisateurController=loader.getController();
    	consulterMaterielParUtilisateurController.RemplirTableTout();;		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }
    @FXML private void EnleverMaterielUtilisateur() throws NamingException
    {
    	Materiel materiel= new Materiel();
    	materiel= materiels.getSelectionModel().getSelectedItem();
    	List<Materiel> materiels= new ArrayList<Materiel>();
    	materiels=utilisateur.getMateriels();
    	materiels.remove(materiel);
    	utilisateur.setMateriels(materiels);
    	utilities.context=new InitialContext();
    	utilities.utilisateurServicesRemote=(UtilisateurServicesRemote) utilities.context.lookup(utilities.utilRemote);
    	utilities.utilisateurServicesRemote.update(utilisateur);
    	//utilities.closeStage(Ajouter);
    	Stage stage = (Stage) Ajouter.getScene().getWindow();
    	stage.show();
    	utilities.GenererAlerte("materielEnlever");
    }
    @FXML
    private void AffecterMaterielUtilisateur() throws NamingException
    {
    	Materiel materiel= new Materiel();
    	materiel=materiels.getSelectionModel().getSelectedItem();
    	List<Materiel> materiels= new ArrayList<Materiel>();
    	materiels=utilisateur.getMateriels();
    	materiels.add(materiel);
    	utilisateur.setMateriels(materiels);
    	utilities.utilisateurServicesRemote=(UtilisateurServicesRemote) utilities.context.lookup(utilities.utilRemote);
    	utilities.utilisateurServicesRemote.update(utilisateur);
    	Stage stage = (Stage) Ajouter.getScene().getWindow();
    	stage.close();
    	
    	utilities.GenerertAletrtOk("Materiel affecter avec succes");
    	
    }
    

}
