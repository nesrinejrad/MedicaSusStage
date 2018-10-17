/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;



import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entities.Materiel;
import Entities.Utilisateur;
import Services.MaterielService;
import Services.UtilisateurServices;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GestionMaterielsController implements Initializable {
	@FXML
    private AnchorPane imageMedicaSud;
	@FXML
    private TableColumn<Materiel, String> referenceMateriel;
    @FXML
    private TableColumn<Materiel, String> marqueMAteriel;
    @FXML
    private TableColumn<Materiel, LocalDate> DateAchatMAteriel;
    @FXML
    private TableColumn<Materiel, String>CodeMateriel;
    @FXML
    private TableColumn<Materiel, String> DureeGarantie;
    @FXML
    private TableColumn<Materiel, Date> Fournisseur;
    @FXML
    private JFXButton Accueil;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton modifier;
    @FXML
    private JFXButton Supprimer;
    @FXML
    private JFXButton ListeUtilisateur;
    @FXML
    private JFXButton ListeIntervention;
    @FXML
    private JFXButton Deconnexion;
    Utilites utilies= new Utilites();
    static Utilisateur utilisateur= new Utilisateur();
    @FXML
    private TableView<Materiel> materiels;
    private ObservableList<Materiel> data;
    private ObservableList<Utilisateur> dataUser;

    @FXML
    private TableView<Utilisateur> UtilisateurTableView;
    @FXML
    private TableColumn<Utilisateur, String> codeUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> nomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> prenomUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> emailUtilisateur;
    @FXML
    private TableColumn<Utilisateur, String> fonctionUtilisateur;
    MaterielService ms= new MaterielService();
    UtilisateurServices us= new UtilisateurServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilies.backgroundImage(imageMedicaSud);

    	List<Materiel> materiels= new ArrayList<Materiel>();
   	   try {
		materiels=ms.displayAll();
		System.out.println("size materiem="+materiels.size());
		data=FXCollections.observableList(materiels);	
		System.out.println("data materiem="+data.size());
		UtilisateurTableView.setVisible(false);

		referenceMateriel.setCellValueFactory(new PropertyValueFactory<>("reference"));
	   	  marqueMAteriel.setCellValueFactory(new PropertyValueFactory<>("marque"));
	   	  DateAchatMAteriel.setCellValueFactory(new PropertyValueFactory<>("date achat"));
	   	  DureeGarantie.setCellValueFactory(new PropertyValueFactory<>("dureeGarantie"));
	 	  Fournisseur.setCellValueFactory(new PropertyValueFactory<>("fournisseur"));
	 	  CodeMateriel.setCellValueFactory(new PropertyValueFactory<>("id"));
	   		this.materiels.setItems(data);

   	   } catch (SQLException ex) { 
                Logger.getLogger(GestionMaterielsController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilies.newStageWithOldStage("AjouterMateriel.fxml");
    }

    @FXML
    private void modifierAction(ActionEvent event) throws IOException {
      	Materiel materiel= materiels.getSelectionModel().getSelectedItem();
    	AjouterMaterielController ajouterMaterielController= new AjouterMaterielController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AjouterMateriel.fxml"));
        Parent root = (Parent) loader.load();
    	ajouterMaterielController=loader.getController();
    	ajouterMaterielController.RemplirCahmp(materiel);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);
    	

    }

    @FXML
    private void supprimerAction(ActionEvent event) throws NamingException {
            try {
                Materiel materiel= new Materiel();
                materiel=materiels.getSelectionModel().getSelectedItem();
                ms.supprimerMAteriel(materiel);
                utilies.GenerertAletrtOk("materiel supprimé avec succes");    
                this.initialize(null, null);
            } catch (SQLException ex) {
                Logger.getLogger(GestionMaterielsController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void ListeUtilisateurAction(ActionEvent event) throws IOException, NamingException, SQLException {
    	
    	Materiel materiel= new Materiel();
    	materiel=this.materiels.getSelectionModel().getSelectedItem();
        UtilisateurServices us= new UtilisateurServices();
    	List<Utilisateur> utilisateurs= new ArrayList<Utilisateur>();
    	//List<Utilisateur> utilisateurs1= new ArrayList<Utilisateur>();
        utilisateurs=us.UtilisateurMateriel(materiel.getId());
    	/*for (Utilisateur utilisateur : utilisateurs1) {
			boolean exist=false;
			List<Materiel> materiels= new ArrayList<Materiel>();
			materiels=utilisateur.getMateriels();
			System.out.println("size materiel"+materiels.size());
			for (Materiel materiel1 : materiels) {
				if(materiel.getId().equals(materiel1.getId()))
				{
					System.out.println("ok");
					exist=true;
				}}
				if(exist)
				{
					utilisateurs.add(utilisateur);
				}
			
		}*/
    	if(utilisateurs.size()!=0)
    	{UtilisateurTableView.setVisible(true);
    	this.materiels.setVisible(false);
    		dataUser=FXCollections.observableList(utilisateurs);
    	codeUtilisateur.setCellValueFactory(new PropertyValueFactory<>("code"));
   	    nomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("nom"));
   	    prenomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("prenom"));
   	    emailUtilisateur.setCellValueFactory(new PropertyValueFactory<>("email"));
   	 fonctionUtilisateur.setCellValueFactory(new PropertyValueFactory<>("fonction"));
	    UtilisateurTableView.setItems(dataUser);
	    materiel= new Materiel();}
    	else
    	{
    		utilies.GenererAlerte("ce matériel n'a pas encore d'utilisateurs");
    	}
    	
    }

    @FXML
    private void ListeInterventionAction(ActionEvent event) throws IOException, SQLException, NamingException {
          Materiel materiel= new Materiel();
    	materiel=materiels.getSelectionModel().getSelectedItem();
    	ConsulterInterventionParMaterielController consulterMaterielParUtilisateurController= new ConsulterInterventionParMaterielController();
    	//consulterMaterielParUtilisateurController.m=materiel;
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterInterventionParMateriel.fxml"));
        Parent root = (Parent) loader.load();
    	consulterMaterielParUtilisateurController=loader.getController();
    	consulterMaterielParUtilisateurController.RemplirTableTout(materiel);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
    	utilies.newStage(Accueil, "AccueilAdmin.fxml", "Accueil");
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
    	utilies.newStage(Accueil, "login.fxml", "login");
    }
    
}
