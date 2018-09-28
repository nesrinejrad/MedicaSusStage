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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.MedicaSud.entities.Fournisseur;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.services.FournisseurServicesRemote;
import tn.MedicaSud.services.MaterielServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GestionFournisseursController implements Initializable {
	@FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton Supprimer;
    @FXML
    private JFXButton ListeMateriel;
    @FXML
    private JFXButton Accueil;
    @FXML
    private JFXButton Deconnexion;
    @FXML
    private TableView<Fournisseur> FournisseurTableView;
    @FXML
    private TableColumn<Fournisseur, String> codeFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> nomFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> adresseFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> emailFournisseur;
    @FXML
    private TableColumn<Fournisseur, String> telephoneFournisseur;
    Utilites utilities= new Utilites();
    private ObservableList<Fournisseur> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilities.backgroundImage(imageMedicaSud);
    	
    	
    	  try {
			utilities.context= new InitialContext();
			utilities.fournisseurServicesRemote= (FournisseurServicesRemote) utilities.context.lookup(utilities.FournisseurRemote);
	  		List<Fournisseur> fournisseurs= new ArrayList<Fournisseur>();
	  		fournisseurs=utilities.fournisseurServicesRemote.findAll();
	  		data=FXCollections.observableList(fournisseurs);
	  		codeFournisseur.setCellValueFactory(new PropertyValueFactory<>("id"));
	  	   	  nomFournisseur.setCellValueFactory(new PropertyValueFactory<>("nom"));
	  	   	  adresseFournisseur.setCellValueFactory(new PropertyValueFactory<>("adresse"));
	  	   	  emailFournisseur.setCellValueFactory(new PropertyValueFactory<>("email"));
	  	 	  telephoneFournisseur.setCellValueFactory(new PropertyValueFactory<>("telephone"));
	  	 	  FournisseurTableView.setItems(data);
		} catch (NamingException e) {
			
		}
  	
    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilities.newStageWithOldStage("AjouterFournisseur.fxml");
    }

    @FXML
    private void modifierAction(ActionEvent event) throws IOException, NamingException {
     	Fournisseur fournisseur= FournisseurTableView.getSelectionModel().getSelectedItem();
    	AjouterFournisseurController ajouterFournisseurController= new AjouterFournisseurController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterFournisseur.fxml"));
        Parent root = (Parent) loader.load();
    	ajouterFournisseurController=loader.getController();
    	ajouterFournisseurController.modifFournisseur(fournisseur);
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);
    }

    @FXML
    private void supprimerAction(ActionEvent event) throws NamingException {
    	Fournisseur fournisseur=FournisseurTableView.getSelectionModel().getSelectedItem();
    	utilities.context= new InitialContext();
    	utilities.fournisseurServicesRemote= (FournisseurServicesRemote) utilities.context.lookup(utilities.FournisseurRemote);
    	utilities.fournisseurServicesRemote.delete(fournisseur);
      utilities.GenerertAletrtOk("fournisseur supprimé");
    	this.initialize(null, null);

    }

    @FXML
    private void ListeMAterielAction(ActionEvent event) throws IOException {
    	Fournisseur fournisseur= FournisseurTableView.getSelectionModel().getSelectedItem();
    	ConsulterMaterielFournisseurController consulterMaterielFournisseurController= new ConsulterMaterielFournisseurController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterMaterielFournisseur.fxml"));
        Parent root = (Parent) loader.load();
    	consulterMaterielFournisseurController=loader.getController();
    	consulterMaterielFournisseurController.remplirTableau(fournisseur);
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);
    	
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "AccueilAdmin.fxml", "Accueil");
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "login.fxml", "login");
    }
    
}
