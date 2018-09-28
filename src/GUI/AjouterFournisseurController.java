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
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javassist.tools.framedump;
import tn.MedicaSud.entities.Fournisseur;
import tn.MedicaSud.entities.Role;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.FournisseurServicesRemote;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterFournisseurController implements Initializable {

    @FXML
    private Label idLabel;
	 @FXML
	    private JFXTextField IdentifiantFournisseur;
	    @FXML
	    private JFXTextField NomFournisseur;
	    @FXML
	    private JFXTextField MailFournisseur;
	    @FXML
	    private JFXTextField AdresseFournisseur;
	    @FXML
	    private JFXTextField TéléphoneFournisseur;
	    @FXML
	    private JFXButton Enregistrer;
	    Utilites utilities= new Utilites();

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	     
	        }    
	    public void modifFournisseur(Fournisseur fournisseur1) throws NamingException
	    {
	    	   idLabel.setVisible(false);
		        IdentifiantFournisseur.setVisible(false);
		        IdentifiantFournisseur.setText(fournisseur1.getId());
		        NomFournisseur.setText(fournisseur1.getNom());
		        AdresseFournisseur.setText(fournisseur1.getAdresse());
		        TéléphoneFournisseur.setText(fournisseur1.getTelephone());
		        MailFournisseur.setText(fournisseur1.getEmail());
		        
		        Fournisseur fournisseur= new Fournisseur();
		        fournisseur.setId(idLabel.getText());
		        fournisseur.setEmail(MailFournisseur.getText());
		        fournisseur.setAdresse(AdresseFournisseur.getText());
		        fournisseur.setTelephone(TéléphoneFournisseur.getText());
		        fournisseur.setNom(NomFournisseur.getText());
		       
		        
	    }

	    @FXML
	    private void EnregistrerFournisseur(ActionEvent event) throws NamingException {
	    	Fournisseur fournisseur= new Fournisseur();
	    	fournisseur.setId(IdentifiantFournisseur.getText());
	    	fournisseur.setEmail(MailFournisseur.getText());
	    	fournisseur.setAdresse(AdresseFournisseur.getText());
	    	fournisseur.setNom(NomFournisseur.getText());
	    	fournisseur.setTelephone(TéléphoneFournisseur.getText());
	    	utilities.context= new InitialContext();
	    	utilities.fournisseurServicesRemote= (FournisseurServicesRemote) utilities.context.lookup(utilities.FournisseurRemote);
	    	utilities.fournisseurServicesRemote.update(fournisseur);
	    	utilities.closeStage(Enregistrer);
	    	utilities.GenerertAletrtOk("fournisseur ajouté avec succés");
	    }
}
