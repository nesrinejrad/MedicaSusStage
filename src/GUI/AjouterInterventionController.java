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
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Fournisseur;
import tn.MedicaSud.entities.Intervention;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Role;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Ticket;
import tn.MedicaSud.entities.TypeMateriel;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.FournisseurServicesRemote;
import tn.MedicaSud.services.InterventionServicesRemote;
import tn.MedicaSud.services.MaterielServicesRemote;
import tn.MedicaSud.services.UtilisateurServicesRemote;
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
    	
    	{intervention.setId(IdentifiantIntervention.getText());
    	intervention.setDescription(DescriptionIntervention.getText());
    	intervention.setDateIntervention(DateIntervention.getValue());
    	intervention.setMateriel(ticket.getMateriel());
    	intervention.setPeriode(0);
    	intervention.setTicket(ticket);
    	intervention.setEtatIntervention(EtatTicket.valueOf("enCours"));
    	
    	utilities.context= new InitialContext();
    	utilities.interventionServicesRemote= (InterventionServicesRemote) utilities.context.lookup(utilities.interventionRemote);
    	utilities.interventionServicesRemote.update(intervention);
    	utilities.GenerertAletrtOk("intervention enregistrée");
    	
    	
    }
    }
    
    

    
    
}
