/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import GUI.Accueil_clientController;
import tn.MedicaSud.app.client.gui.Utilites;
import tn.MedicaSud.entities.EtatTicket;
import tn.MedicaSud.entities.Materiel;
import tn.MedicaSud.entities.Ticket;
import tn.MedicaSud.services.TicketSerciesRemote;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

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
public class Consulter_ticketController implements Initializable {
	 @FXML
	    private ImageView imgConsulterDemande;
	  @FXML
	    private JFXButton  ConsulterDemande ;
	

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
    @FXML
    private JFXToggleButton ETatTicketButton;
    @FXML
    private ImageView AjouterTicket;
    @FXML
    private ImageView SupprimerTicket;
    @FXML
    private JFXButton NouveauTicket;
    @FXML
    private JFXButton DemandeMateriel;
    @FXML
    private JFXButton Deconnexion;
    @FXML
    private JFXButton EditerProfile;
    @FXML
    private TableView<Ticket> tickets;

       Utilites utilites= new Utilites();
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton consulterTicket;
    @FXML
    private JFXButton ConsulterMateriel;
    @FXML
    private ImageView imgConsulterTicket;
    @FXML
    private ImageView ImgNouveauTicket;
    @FXML
    private ImageView ImgDemandeMateriel;
    @FXML
    private ImageView imgConsulterMateriel;
    @FXML
    private ImageView ImgEditerProfile;
    @FXML
    private ImageView ImageDeconnexion;
    private ObservableList<Ticket> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              utilites.backgroundImage(imageMedicaSud);
              Image img = new Image("Assets/icons8-voir-les-détails-50.png");
         	   imgConsulterTicket.setImage(img);
         	   
         	    img = new Image("Assets/icons8-poste-de-travail-64.png");
       	   imgConsulterMateriel.setImage(img);
       	   
       	   img = new Image("Assets/icons8-ajouter-32.png");
       	   ImgNouveauTicket.setImage(img);
       	   
       	   img = new Image("Assets/Demande.png");
       	   ImgDemandeMateriel.setImage(img);
       	   
       	   img = new Image("Assets/icons8-editer-le-fichier-80.png");
       	   ImgEditerProfile.setImage(img);
       	   
       	   img = new Image("Assets/icons8-connexion-filled-50.png");
       	   ImageDeconnexion.setImage(img);
       	   
       	imgAccceuil.setImage(img);
       	  
			try {
				utilites.context = new InitialContext();
				utilites.ticketSerciesRemote=(TicketSerciesRemote) utilites.context.lookup(utilites.ticketRemote);
				List<Ticket> tickets=utilites.ticketSerciesRemote.findAll();
				List<Ticket> ticketsFinal= new ArrayList<Ticket>();
				System.out.println(tickets.size());
				for (Ticket ticket : tickets) {
					if(ticket.getUtilisateur().getCode().equals(Accueil_clientController.utilisateurConnecte.getCode()))
					{
						ticketsFinal.add(ticket);
					}
					System.out.println("final="+ticketsFinal.size());
					data=FXCollections.observableList(ticketsFinal);
				}
			} catch (NamingException e) {
			
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
			 	 
			 	 this.tickets.setItems(data);
			 	
		
       	   
       	   
       	   

    }    


    @FXML
    private void AjouterTicketAction(MouseEvent event) {
    }

    @FXML
    private void SupprimerTicketAction(MouseEvent event) {
    }


    @FXML
    private void NouveauTicketAction(ActionEvent event) throws IOException {
              utilites.newStage(Deconnexion, "Ajouter_tickets.fxml", "nouveau ticket");
    }

    @FXML

    private void DeconnexionAction(ActionEvent event) throws IOException {
          utilites.newStage(Deconnexion, "login.fxml","login");
          Accueil_clientController.utilisateurConnecte=null;

    }
    @FXML
    private void retourAcceuil() throws IOException
    {
    	   utilites.newStage(Deconnexion, "Accueil_client.fxml","consulter tickets");
    	
    }
 
    @FXML
    private void EditerProfileAction(ActionEvent event) throws IOException {
           utilites.newStageWithOldStage("EditerMotDePasse.fxml");     
    }
    @FXML
    private void consulterTicketAction(ActionEvent event) throws IOException {
             utilites.newStage(Deconnexion, "Consulter_ticket.fxml"," consulter tickets");
}
    @FXML
    private void ConsulterMaterielAction(ActionEvent event) throws IOException {
           utilites.newStage(Deconnexion, "Consulter_matériel.fxml", "consulter matériels");
    }
    @FXML
    private void DemandeMaterielAction(ActionEvent event) throws IOException {
           utilites.newStage(Deconnexion, "Demande_materiel.fxml", " demande matériel");
    }
    @FXML
    private void ETatTicketAction(ActionEvent event) throws IOException {
    	if (ETatTicketButton.isSelected())
    	{try {
    		data.clear();
			utilites.context = new InitialContext();
			utilites.ticketSerciesRemote=(TicketSerciesRemote) utilites.context.lookup(utilites.ticketRemote);
			List<Ticket> tickets=utilites.ticketSerciesRemote.findAll();
			List<Ticket> ticketsFinal= new ArrayList<Ticket>();
			System.out.println(tickets.size());
			for (Ticket ticket : tickets) {
				if(ticket.getUtilisateur().getCode()==Accueil_clientController.utilisateurConnecte.getCode())
				{  if(ticket.getEtatTicket().equals(EtatTicket.valueOf("résolu")))
				{	ticketsFinal.add(ticket);
}
				}
				
				data=FXCollections.observableList(ticketsFinal);
				for (Ticket ticket1 : data) {
					System.out.println(ticket1.getEtatTicket());
				}
				this.tickets.setItems(null);
				this.tickets.setItems(data);
				
			}
		} catch (NamingException e) {
		
		}
    	}
    	else
           utilites.newStage(Deconnexion, "Consulter_ticket.fxml", " demande matériel");
    }
    @FXML
    private void ConsulterDemandeMaterielAction(ActionEvent event) throws IOException {
                  utilites.newStage(Deconnexion, "ConsulterDemandeMateriel.fxml", "login");

    }
}
