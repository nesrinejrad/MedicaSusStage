/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Utiles.SendMail;

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
import Entities.Fournisseur;
import Entities.Role;
import Entities.StatutTicket;
import Entities.Utilisateur;
import Services.FournisseurService;

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
            FournisseurService fs= new FournisseurService();
            boolean modif=false;
	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	     
	        }    
	    public void modifFournisseur(Fournisseur fournisseur1) throws NamingException
	    {
	    	   idLabel.setVisible(false);
                      this.modif=true;
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
                if(modif==false)
                {fs.ajouterFourniseur(fournisseur);
	    	utilities.closeStage(Enregistrer);
	    	utilities.GenerertAletrtOk("fournisseur ajouté avec succés");}
                else
                {
                    fs.modifierFournisseur(fournisseur);
                    	    	utilities.closeStage(Enregistrer);
                                	    	utilities.GenerertAletrtOk("fournisseur modifié avec succés");}


                
	    }
}
