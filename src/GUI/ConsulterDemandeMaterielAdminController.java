/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import com.jfoenix.controls.JFXButton;
import javafx.stage.Stage;
import javafx.util.Callback;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import tn.MedicaSud.entities.Demande;
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Role;
import tn.MedicaSud.entities.StatutTicket;
import tn.MedicaSud.entities.Ticket;
import tn.MedicaSud.entities.Utilisateur;
import tn.MedicaSud.services.DemandeServicesRemote;
import tn.MedicaSud.services.MaterielServicesRemote;
import tn.MedicaSud.services.TicketSerciesRemote;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConsulterDemandeMaterielAdminController implements Initializable {
    
    @FXML
    private JFXButton Ajouter;
    @FXML
    private TableView<Demande> listeDemandes;
    @FXML
    private TableColumn<Demande, String> type;
    @FXML
    private TableColumn<Demande, String> description;
    @FXML
    private TableColumn<Demande, Date> date;
    @FXML
    private TableColumn<Demande, Utilisateur> utilisateure;
    @FXML
    private TableColumn<Demande,StatutTicket > status;

    @FXML
    private JFXButton enlever;
    @FXML
    private JFXButton Affecter;
   static Utilisateur utilisateur= new Utilisateur();
    Utilites utilities= new Utilites();
    private ObservableList<Demande> data;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
    		utilities.context = new InitialContext();
    		utilities.demandeServicesRemote=(DemandeServicesRemote) utilities.context.lookup(utilities.demandeRemote);
    		List<Demande> demandes=utilities.demandeServicesRemote.findAll();
    		data=FXCollections.observableList(demandes);
    	} catch (NamingException e) {
    		
    	}
    	  type.setCellValueFactory(new PropertyValueFactory<>("type"));
     	  description.setCellValueFactory(new PropertyValueFactory<>("description"));
     	  date.setCellValueFactory(new PropertyValueFactory<>("date"));
     	  status.setCellValueFactory(new PropertyValueFactory<>("status"));
     	 utilisateure.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
     	 this.listeDemandes.setItems(data);

    }
    public void remplir() throws NamingException
    {
    	
			
		 	
    }
    }

