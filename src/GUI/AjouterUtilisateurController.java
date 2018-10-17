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
import Entities.Role;
import Entities.StatutTicket;
import Entities.Utilisateur;
import static GUI.AffecterUtilisateurMaterielController.utilisateur;
import Services.UtilisateurServices;
import java.sql.SQLException;

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
    UtilisateurServices us = new UtilisateurServices();

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
    private void EnregistrerUtilisateur(ActionEvent event) throws NamingException, IOException, SQLException {
    	Utilisateur utilisateur= new Utilisateur();
    	utilisateur.setCode(IdentifientText.getText());
    	utilisateur.setEmail(AdressemailText.getText());
    	utilisateur.setNom(NomText.getText());
    	utilisateur.setPrenom(PrenomText.getText());
    	utilisateur.setPassword(MotDePasseText.getText());
    	utilisateur.setRole(Role.valueOf((String) RoleUtilisateur.getValue()));
        Utilisateur u1= new Utilisateur();
        u1=null;
        u1=us.rechercherUtilisateur(utilisateur.getCode());
        if(u1==null)
        {us.ajouterUtilisateur(utilisateur);
    	utilities.closeStage(Enregistrer);
    	utilities.GenerertAletrtOk("utilisateur Ajouter avec succes");
    	SendMail sm =new SendMail();
		  sm.sendmail("medicasudapplication@gmail.com",utilisateur.getEmail(), "medicasud123", "your login to helpDesk platfrom \n Login:"+utilisateur.getEmail()+" \n Password :"+utilisateur.getPassword());}
        else
        {
            us.modifierUtilisateur(utilisateur);
            utilities.closeStage(Enregistrer);
                	SendMail sm =new SendMail();
                        
		  sm.sendmail("medicasudapplication@gmail.com",utilisateur.getEmail(), "medicasud123", "your login to helpDesk platfrom \n Login:"+utilisateur.getEmail()+" \n Password :"+utilisateur.getPassword());}

            
        
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
