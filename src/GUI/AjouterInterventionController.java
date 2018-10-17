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
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entities.EtatTicket;
import Entities.Fournisseur;
import Entities.Intervention;
import Entities.Materiel;
import Entities.Role;
import Entities.StatutTicket;
import Entities.Ticket;
import Entities.TypeMateriel;
import Entities.Utilisateur;
import Services.InterventionServices;
import java.sql.Date;
import javafx.scene.text.*;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterInterventionController implements Initializable {
   @FXML
    private JFXTextField IdentifiantIntervention;
    @FXML
    private JFXTextField DescriptionIntervention;
    @FXML
    private DatePicker DateIntervention;
    @FXML
    private JFXTextField PeriodeIntervention;
    @FXML
    private Label labelPeriode;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private Label idLabel;
    static Ticket ticket= new Ticket();
    Utilites utilities= new Utilites();
    static Materiel materiel= new Materiel();
    @FXML
    private Hyperlink externe;
    @FXML
    private Label labelEtat;
    @FXML
    private JFXComboBox<?> etatIntervention;
    InterventionServices is= new InterventionServices();
    boolean modif=false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 
    	System.out.println(ticket.toString());
    	PeriodeIntervention.setVisible(false);
    	labelPeriode.setVisible(false);
    	labelEtat.setVisible(false);
    	etatIntervention.setVisible(false);

     	   
    }    
    public void RemplirCahmp(Materiel materiel) throws NamingException
    {	
    }

    @FXML
    private void EnregistrerIntervetnion(ActionEvent event) throws NamingException  {
    	
    	Intervention intervention= new Intervention();
    	//intervention=null;
        System.out.println(IdentifiantIntervention.getText());
    	intervention.setId(IdentifiantIntervention.getText());
    	intervention.setDescription(DescriptionIntervention.getText());
    	intervention.setDateIntervention(Date.valueOf(DateIntervention.getValue()));
    	intervention.setMateriel(ticket.getMateriel());
    	intervention.setPeriode(0);
    	intervention.setTicket(ticket.getId());
    	intervention.setEtatIntervention(EtatTicket.valueOf("enCours"));
    	
        is.ajouterIntervention(intervention);    	
        utilities.GenerertAletrtOk("intervention enregistrée");
    	
    	
    }
    }
    
    

    
    
