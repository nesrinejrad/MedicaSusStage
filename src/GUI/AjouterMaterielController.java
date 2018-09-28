/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import com.jfoenix.controls.JFXButton;



import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Utilities.SendMail;


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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.MedicaSud.entities.Fournisseur;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Role;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.TypeMateriel;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.FournisseurServicesRemote;
import tn.MedicaSud.services.MaterielServicesRemote;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterMaterielController implements Initializable {
	@FXML
    private JFXTextField IdentifiantMateriel;
    @FXML
    private JFXTextField DescriptionMateriel;
    @FXML
    private JFXTextField MarqueMateriel;
    @FXML
    private DatePicker DateAchatMateriel;
    @FXML
    private JFXTextField DureeGarantieMateriel;
    @FXML
    private JFXComboBox<String> TypeMaterielC;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private JFXComboBox<Fournisseur> FournisseurMateriel;
    @FXML
    private JFXButton Enregistrer1;
    @FXML
    private Hyperlink NouveauFournisseur;
    @FXML
    private Label idLabel;
    Utilites utilities= new Utilites();
    private ObservableList<String> typeMterielData=FXCollections.observableArrayList();
    private ObservableList<Fournisseur> fournissursData=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	  TypeMateriel[] data=TypeMateriel.values();
     	   for (TypeMateriel typeMateriel : data) {
   		typeMterielData.add(String.valueOf(typeMateriel));
   	}
     	   TypeMaterielC.setItems(typeMterielData);
     	   
     	   try {
			utilities.context= new InitialContext();
			utilities.fournisseurServicesRemote= (FournisseurServicesRemote) utilities.context.lookup(utilities.FournisseurRemote);
			List<Fournisseur> fournisseurs= new ArrayList<Fournisseur>();
			fournisseurs= utilities.fournisseurServicesRemote.findAll();
			fournissursData=FXCollections.observableArrayList(fournisseurs);
			FournisseurMateriel.setItems(fournissursData);
		} catch (NamingException e) {
			
		}
     	   
     	   
     	   
    }    
    public void RemplirCahmp(Materiel materiel)
    {	
    	IdentifiantMateriel.setText(materiel.getId());
    	IdentifiantMateriel.setVisible(false);
    	idLabel.setVisible(false);
    	DateAchatMateriel.setValue(materiel.getDateAchat());
    	DescriptionMateriel.setText(materiel.getReference());
    	DureeGarantieMateriel.setText(String.valueOf(materiel.getDureeGarantie()));
    	MarqueMateriel.setText(materiel.getMarque());
    
    }

    @FXML
    private void EnregistrerMateriel(ActionEvent event) throws NamingException {
    	Materiel materiel= new Materiel();
    	materiel.setId(IdentifiantMateriel.getText());
    	materiel.setDureeGarantie(Integer.valueOf(DureeGarantieMateriel.getText()));
    	materiel.setFournisseur(FournisseurMateriel.getValue());
    	materiel.setMarque(MarqueMateriel.getText());
    	materiel.setReference(DescriptionMateriel.getText());
    	materiel.setTypeMateriel(TypeMateriel.valueOf(TypeMaterielC.getValue()));
    	materiel.setDateAchat(DateAchatMateriel.getValue());
    	utilities.context= new InitialContext();
    	utilities.materielServicesRemote=(MaterielServicesRemote) utilities.context.lookup(utilities.materielRemote);
    	utilities.materielServicesRemote.update(materiel);
    	utilities.closeStage(Enregistrer);
    	utilities.GenerertAletrtOk("materiel ajouté avec succées");
    }

    @FXML
    private void AffecterUtilisateurMAteriel(ActionEvent event) throws NamingException, IOException {
    	Materiel materiel= new Materiel();
    	materiel.setId(IdentifiantMateriel.getText());
    	materiel.setDureeGarantie(Integer.valueOf(DureeGarantieMateriel.getText()));
    	materiel.setFournisseur(FournisseurMateriel.getValue());
    	materiel.setMarque(MarqueMateriel.getText());
    	materiel.setReference(DescriptionMateriel.getText());
    	materiel.setTypeMateriel(TypeMateriel.valueOf(TypeMaterielC.getValue()));
    	materiel.setDateAchat(DateAchatMateriel.getValue());
    	utilities.context= new InitialContext();
    	utilities.materielServicesRemote=(MaterielServicesRemote) utilities.context.lookup(utilities.materielRemote);
    	utilities.materielServicesRemote.update(materiel);
    	utilities.closeStage(Enregistrer);
    	AffecterUtilisateurMaterielController affecterUtilisateurMaterielController= new AffecterUtilisateurMaterielController();
    	affecterUtilisateurMaterielController.materiel=materiel;;
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AffecterUtilisateurMateriel.fxml"));
        Parent root = (Parent) loader.load();
    	affecterUtilisateurMaterielController=loader.getController();
    	affecterUtilisateurMaterielController.RemplirTable(materiel);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    	
    }

    @FXML
    private void NouveauFournisseurAction(ActionEvent event) throws IOException {
    	utilities.newStageWithOldStage("AjouterFournisseur.fxml");
    }
    
}
