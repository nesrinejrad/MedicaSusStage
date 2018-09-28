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
import tn.MedicaSud.entities.Role;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterUtilisateurController implements Initializable {
	@FXML
	private Label idLabel;
    @FXML
    private JFXTextField IdentifientText;
    @FXML
    private JFXTextField NomText;
    @FXML
    private JFXTextField PrenomText;
    @FXML
    private JFXTextField AdressemailText;
    @FXML
    private JFXTextField MotDePasseText;
    @FXML
    private JFXComboBox<String> RoleUtilisateur;
    @FXML
    private JFXButton Enregistrer;
    
    Utilites utilities= new Utilites();
    ObservableList<String> roleData=FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	Role[] roles=Role.values();
     	 for (Role role : roles) {
   		roleData.add(String.valueOf(role));
   	}
        RoleUtilisateur.setItems(roleData);       
    }    


    @FXML
    private void EnregistrerUtilisateur(ActionEvent event) throws NamingException, IOException {
    	Utilisateur utilisateur= new Utilisateur();
    	utilisateur.setCode(IdentifientText.getText());
    	utilisateur.setEmail(AdressemailText.getText());
    	utilisateur.setNom(NomText.getText());
    	utilisateur.setPrenom(PrenomText.getText());
    	utilisateur.setPassword(MotDePasseText.getText());
    	utilisateur.setRole(Role.valueOf((String) RoleUtilisateur.getValue()));
    	utilities.context= new InitialContext();
    	utilities.utilisateurServicesRemote=(UtilisateurServicesRemote) utilities.context.lookup(utilities.utilRemote);
    	utilities.utilisateurServicesRemote.update(utilisateur);
    	utilities.closeStage(Enregistrer);
    	utilities.GenerertAletrtOk("utilisateur Ajouter avec succes");
    	SendMail sm =new SendMail();
		  sm.sendmail("medicasudapplication@gmail.com",utilisateur.getEmail(), "medicasud123", "your login to helpDesk platfrom \n Login:"+utilisateur.getEmail()+" \n Password :"+utilisateur.getPassword());
    }
    
    public void RemplirCahmp(Utilisateur utilisateur)
    {	
    	IdentifientText.setText(utilisateur.getCode());
    	IdentifientText.setVisible(false);
    	idLabel.setVisible(false);
    	MotDePasseText.setText(utilisateur.getPassword());
    	NomText.setText(utilisateur.getNom());
    	PrenomText.setText(utilisateur.getPrenom());
    	AdressemailText.setText(utilisateur.getEmail());
    	RoleUtilisateur.setValue(String.valueOf(utilisateur.getRole()));
    
    }
}
