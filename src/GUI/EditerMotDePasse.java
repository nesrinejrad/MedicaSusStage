/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Accueil_clientController;
import GUI.Utilites;
import Entities.Panne;
import Entities.TypeMateriel;
import Services.UtilisateurServices;

import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EditerMotDePasse implements Initializable {

    @FXML
    private JFXButton ValiderNouvellePanne;
    Utilites utilites= new Utilites();
    @FXML
    private JFXPasswordField mdp1;
    @FXML
    private JFXPasswordField mdp2;
    private ObservableList<String> dateTypeMateriel=FXCollections.observableArrayList();
   
    static int dim1;
    static int dim2;
    
    UtilisateurServices us = new UtilisateurServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    

    }    

    @FXML
    private void ValiderChangerMotDePasse(ActionEvent event) throws NamingException, IOException {
   	System.out.println("mdp1="+mdp1.getText());
    	System.out.println("mdp2="+mdp2.getText());
    	String msg="";
    	if (mdp1.getText()==null) {
    		msg="mot de passe vide!";
    	    utilites.GenererAlerte(msg);
    	}

    	else if (mdp2.getText().equals(null)) {
    		msg=" mot de passe vide!!";
    	    utilites.GenererAlerte(msg);
    	}
    	else if(mdp1.getText().equals(mdp2.getText()))
    	{
    	    Accueil_clientController.utilisateurConnecte.setPassword(mdp1.getText());
    	      utilites.closeStage(ValiderNouvellePanne);
              us.modifierUtilisateur(Accueil_clientController.utilisateurConnecte);    	}
    	else
    	{	
    		msg=" mot de passes non identiques!!";
    	    utilites.GenererAlerte(msg);}
     
    }
       
	     
    }

