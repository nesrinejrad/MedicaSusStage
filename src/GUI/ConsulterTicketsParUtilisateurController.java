/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import Entities.EtatTicket;
import Entities.Materiel;
import Entities.Role;
import Entities.StatutTicket;
import Entities.Ticket;
import Entities.Utilisateur;
import Services.TicketServices;
import java.sql.SQLException;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConsulterTicketsParUtilisateurController implements Initializable {
    
    @FXML
    private JFXButton Ajouter;
    @FXML
    private TableView<Ticket> tickets;
    @FXML
    private TableColumn<Ticket, String> materielTicket;
    @FXML
    private ImageView imgAccceuil;
    @FXML
    private TableColumn<Ticket, Date> DateCreationTicket;
    @FXML
    private TableColumn<Ticket, String> ProblèpmeTicket;
    @FXML
    private TableColumn<Ticket, String> StatutTicket;
    @FXML
    private TableColumn<Ticket, String> DescriptionTicket;
    @FXML
    private TableColumn<Ticket, EtatTicket> etatTicket;
    private ObservableList<Ticket> data;
    @FXML
    private JFXButton enlever;
    @FXML
    private JFXButton Affecter;
   static Utilisateur utilisateur= new Utilisateur();
    Utilites utilities= new Utilites();
    TicketServices ts = new TicketServices();
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	   

    }
    public void remplir() throws NamingException, SQLException
    {
    	
			List<Ticket> tickets=ts.displayAll();
			List<Ticket> ticketsFinal= new ArrayList<Ticket>();
			System.out.println("utilisateur="+utilisateur.getCode());
			System.out.println(tickets.size());
			for (Ticket ticket : tickets) {
				System.out.println(ticket.getUtilisateur());
				if(ticket.getUtilisateur().equals(utilisateur.getCode()))
				{	
					ticketsFinal.add(ticket);
				}}
			
				data=FXCollections.observableList(ticketsFinal);
			 materielTicket.setCellValueFactory(new PropertyValueFactory<>("materiel"));
		 	  DescriptionTicket.setCellValueFactory(new PropertyValueFactory<>("discription"));
		 	  DateCreationTicket.setCellValueFactory(new PropertyValueFactory<>("sateCreation"));
		 	  ProblèpmeTicket.setCellValueFactory(new PropertyValueFactory<>("panne"));
		 	  StatutTicket.setCellValueFactory(new PropertyValueFactory<>("statutTicket"));
		 	  etatTicket.setCellValueFactory(new PropertyValueFactory<>("etatTicket"));

		 	 
		 	 this.tickets.setItems(data);
		
    }
    }

