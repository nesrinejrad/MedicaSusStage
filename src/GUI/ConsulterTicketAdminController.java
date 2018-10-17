/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Utilites;
import Entities.EtatTicket;
import Entities.Intervention;
import Entities.Materiel;
import Entities.Ticket;
import Services.TicketServices;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConsulterTicketAdminController implements Initializable {
	 @FXML
	    private ImageView imgConsulterDemande;
	  @FXML
	    private JFXButton  ConsulterDemande ;
	  @FXML
	    private JFXButton Ajouter;
	    @FXML
	    private JFXButton ListeTickets;
	    @FXML
	    private JFXButton ListeInterventions;
	    @FXML
	    private JFXButton Accueil;
	   
	

    @FXML
    private TableColumn<Ticket, String> materielTicket;
    @FXML
    private ImageView imgAccceuil;
    @FXML
    private TableColumn<Ticket, Date> DateCreationTicket;
    @FXML
    private TableColumn<Ticket, Date>  UtilisateurTicket;
    @FXML
    private TableColumn<Ticket, String> ProblèpmeTicket;
    @FXML
    private TableColumn<Ticket, String> StatutTicket;
    @FXML
    private TableColumn<Ticket, String> DescriptionTicket;
    @FXML
    private TableColumn<Ticket, EtatTicket> etatTicket;
    @FXML
    private JFXToggleButton ETatTicketButton;

    @FXML
    private JFXButton Deconnexion;

    @FXML
    private JFXButton AjouterIntervention;
    
    @FXML
    private TableView<Ticket> tickets;

       Utilites utilites= new Utilites();
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton consulterTicket;
    TicketServices ts= new TicketServices();
 

    private ObservableList<Ticket> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
    	utilites.backgroundImage(imageMedicaSud);
    	try {
			List<Ticket> tickets=ts.displayAll();
			data=FXCollections.observableList(tickets);
		} catch (SQLException ex) { 
                 Logger.getLogger(ConsulterTicketAdminController.class.getName()).log(Level.SEVERE, null, ex);
             }
    	materielTicket.setCellValueFactory(new PropertyValueFactory<>("materiel"));
	 	  DescriptionTicket.setCellValueFactory(new PropertyValueFactory<>("discription"));
	 	  DateCreationTicket.setCellValueFactory(new PropertyValueFactory<>("sateCreation"));
	 	  ProblèpmeTicket.setCellValueFactory(new PropertyValueFactory<>("panne"));
	 	  StatutTicket.setCellValueFactory(new PropertyValueFactory<>("statutTicket"));
	 	  etatTicket.setCellValueFactory(new PropertyValueFactory<>("etatTicket"));
	 	 etatTicket.setCellFactory(new Callback<TableColumn<Ticket,EtatTicket>, TableCell<Ticket,EtatTicket>>() {
			public TableCell<Ticket, EtatTicket> call(TableColumn<Ticket, EtatTicket> param) {
					return new TableCell<Ticket,EtatTicket>() 
							{		@Override
									  public void updateItem (EtatTicket t, boolean empty) {
						                    super.updateItem(t, empty);
						                    if (t==  EtatTicket.valueOf("nonTraité") ) {
						                        this.setText(String.valueOf(t));
						                        this.setTextFill(Color.RED);
						                    }
						                    if (t==  EtatTicket.valueOf("résolu") ) {
						                        this.setText(String.valueOf(t));
						                        this.setTextFill(Color.GREEN);
						                    }
								}
							
							};
				}
			});
	 	 UtilisateurTicket.setCellValueFactory(new PropertyValueFactory<>("utilisateur"));
	 	 this.tickets.setItems(data);
                     
       	   
       	   

    }    
    @FXML
    private void AjouterAction(ActionEvent event) {
    }

    @FXML
    private void ListeTicketsAction(ActionEvent event) throws IOException {
    	utilites.newStage(Accueil, "ConsulterTicketAdmin.fxml", "Liste des tickets");
    }

 

    @FXML
    private void ListeInterventionsAction(ActionEvent event) throws IOException {
    	utilites.newStage(Accueil, "ListeInterventions.fxml", "liste des interventions");   }

    

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
    	utilites.newStage(Accueil, "AccueilAdmin.fxml", "Accueil");

    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
    	utilites.newStage(Accueil, "login.fxml", "login");

    }
   
    
    @FXML
    private void ETatTicketAction(ActionEvent event) 
    
    {
        
    	
    }
    @FXML
    private void CreerInterventionAction(ActionEvent event) throws IOException    
    {
    	Ticket ticket= new Ticket();
    	ticket=this.tickets.getSelectionModel().getSelectedItem();
    	AjouterInterventionController ajouterInterventionController= new AjouterInterventionController();
    	ajouterInterventionController.ticket=ticket;
    	System.out.println(ticket.toString());
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterIntervention.fxml"));
        Parent root = (Parent) loader.load();
    	ajouterInterventionController=loader.getController();
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    	
    }
    
}
