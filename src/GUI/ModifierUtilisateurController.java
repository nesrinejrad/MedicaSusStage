/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ModifierUtilisateurController implements Initializable {

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
    private JFXButton Accueil;
    @FXML
    private JFXButton Deconnexion;
    @FXML
    private JFXTextField NomText;
    @FXML
    private JFXTextField PrenomText;
    @FXML
    private JFXTextField AdressemailText;
    @FXML
    private JFXTextField MotDePasseText;
    @FXML
    private JFXComboBox<?> RoleUtilisateur;
    @FXML
    private JFXButton Enregistrer;
    Utilites utilies= new Utilites();
	@FXML
    private AnchorPane imageMedicaSud;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilies.backgroundImage(imageMedicaSud);

    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilies.newStage(Ajouter, "AjouterUtilisateur.fxml", "Ajouter Utilisateur");

    }

    @FXML
    private void modifierAction(ActionEvent event) throws IOException {
    	utilies.newStage(Ajouter, "ModifierUtilisateur.fxml", "Ajouter Utilisateur");

    }

    @FXML
    private void supprimerAction(ActionEvent event) {
    }

    @FXML
    private void ListeMAterielAction(ActionEvent event) {
    }

    @FXML
    private void ListeInterventionAction(ActionEvent event) {
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) {
    }

    @FXML
    private void EnregistrerUtilisateur(ActionEvent event) {
    }
    
}
