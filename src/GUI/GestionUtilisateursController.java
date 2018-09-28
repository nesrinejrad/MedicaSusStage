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

import org.hibernate.internal.util.xml.FilteringXMLEventReader;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GestionUtilisateursController implements Initializable {
	@FXML
    private AnchorPane imageMedicaSud;

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
    private JFXButton Accueil;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton Supprimer;
    @FXML
    private JFXButton ListeMateriel;
    @FXML
    private JFXButton ListeIntervention;
    @FXML
    private JFXButton Deconnexion;
    Utilites utilies= new Utilites();
    private ObservableList<Utilisateur> utilisateursData;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilies.backgroundImage(imageMedicaSud);
    	List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
    	try {
			utilies.context= new InitialContext();
			utilies.utilisateurServicesRemote=(UtilisateurServicesRemote) utilies.context.lookup(utilies.utilRemote );
	    	utilisateurs=utilies.utilisateurServicesRemote.findAll();
	    	System.out.println(utilisateurs.size());
	    	utilisateursData=FXCollections.observableList(utilisateurs);
	   	    codeUtilisateur.setCellValueFactory(new PropertyValueFactory<>("code"));
	   	    nomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("nom"));
	   	    prenomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
	   	    motdepasseUtilisateur.setCellValueFactory(new PropertyValueFactory<>("password"));
	   	    emailUtilisateur.setCellValueFactory(new PropertyValueFactory<>("email"));
	   	    fonctionUtilisateur.setCellValueFactory(new PropertyValueFactory<>("fonction"));
	   	    UtilisateurTableView.setItems(utilisateursData);




    	} catch (NamingException e) {
			
		}
    	
    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilies.newStageWithOldStage("AjouterUtilisateur.fxml");
    }

    @FXML
    private void modifierAction(ActionEvent event) throws IOException {
    	Utilisateur utilisateur= UtilisateurTableView.getSelectionModel().getSelectedItem();
    	System.out.println(utilisateur.toString());
    	AjouterUtilisateurController ajouterUtilisateurController= new AjouterUtilisateurController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterUtilisateur.fxml"));
        Parent root = (Parent) loader.load();
    	ajouterUtilisateurController=loader.getController();
    	ajouterUtilisateurController.RemplirCahmp(utilisateur);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);

    }

    @FXML
    private void supprimerAction(ActionEvent event) throws NamingException {
    	Utilisateur utilisateur= new Utilisateur();
    	utilisateur=UtilisateurTableView.getSelectionModel().getSelectedItem();
    	utilies.context= new InitialContext();
    	utilies.utilisateurServicesRemote=(UtilisateurServicesRemote) utilies.context.lookup(utilies.utilRemote);
    	utilies.utilisateurServicesRemote.delete(utilisateur);
    	//utilies.closeStage(Accueil);
    	utilies.GenerertAletrtOk("utilisateur Ajouter avec succes");
    	this.initialize(null, null);
    }

    @FXML
    private void ListeMAterielAction(ActionEvent event) throws IOException {
    	Utilisateur utilisateur= new Utilisateur();
    	utilisateur=UtilisateurTableView.getSelectionModel().getSelectedItem();
    	ConsulterMaterielParUtilisateurController consulterMaterielParUtilisateurController= new ConsulterMaterielParUtilisateurController();
    	consulterMaterielParUtilisateurController.utilisateur=utilisateur;
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterMaterielParUtilisateur.fxml"));
        Parent root = (Parent) loader.load();
    	consulterMaterielParUtilisateurController=loader.getController();
    	consulterMaterielParUtilisateurController.RemplirTable(utilisateur);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    private void ListeInterventionAction(ActionEvent event) throws IOException, NamingException {
    	Utilisateur utilisateur= new Utilisateur();
    	utilisateur=UtilisateurTableView.getSelectionModel().getSelectedItem();
    	ConsulterTicketsParUtilisateurController consulterTicketsParUtilisateurController= new ConsulterTicketsParUtilisateurController();
    	consulterTicketsParUtilisateurController.utilisateur=utilisateur;
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterTicketsParUtilisateur.fxml"));
        Parent root = (Parent) loader.load();
    	consulterTicketsParUtilisateurController=loader.getController();
    	consulterTicketsParUtilisateurController.remplir();		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
    	utilies.newStage(Accueil, "AccueilAdmin.fxml", "Accueil");
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
    	utilies.newStage(Accueil, "login.fxml", "login");
    }
    
}
