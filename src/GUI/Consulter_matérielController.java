/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Accueil_clientController;
import GUI.Utilites;




import Entities.Materiel;
import Services.MaterielService;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import com.jfoenix.controls.JFXButton;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class Consulter_matérielController implements Initializable {
    @FXML
    private ImageView imgAccceuil;
    @FXML
    private ImageView imgConsulterDemande;
  @FXML
    private JFXButton  ConsulterDemande ;

    @FXML
    private TableColumn<Materiel, String> referenceMateriel;
    @FXML
    private TableColumn<Materiel, String> marqueMAteriel;
    @FXML
    private TableColumn<Materiel, Date> DateAchatMAteriel;
    @FXML
    private JFXButton ConsulterMateriel;
    @FXML
    private JFXButton NouveauTicket;
    @FXML
    private JFXButton DemandeMateriel;
    @FXML
    private JFXButton Deconnexion;
       Utilites utilites= new Utilites();
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton consulterTicket;
    @FXML
    private JFXButton EditerProfile;
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
    @FXML
    private TableView<Materiel> materiels;
    private ObservableList<Materiel> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            utilites.backgroundImage(imageMedicaSud);
            Image img = new Image("Assets/icons8-voir-les-détails-50.png");
            imgConsulterTicket.setImage(img);
            
            img = new Image("Assets/icons8-poste-de-travail-64.png");
            imgConsulterMateriel.setImage(img);
            
            img = new Image("Assets/icons8-ajouter-32.png");
            ImgNouveauTicket.setImage(img);
            
            img = new Image("Assets/demande.png");
            ImgDemandeMateriel.setImage(img);
            
            img = new Image("Assets/icons8-editer-le-fichier-80.png");
            ImgEditerProfile.setImage(img);
            
            img = new Image("Assets/icons8-connexion-filled-50.png");
            ImageDeconnexion.setImage(img);
            
            imgAccceuil.setImage(img);;
            // TODO
            List<Materiel> materiels= new ArrayList<Materiel>();
            MaterielService ms = new MaterielService();
            materiels=ms.displayAllMaterielParUtilisateur(Accueil_clientController.utilisateurConnecte.getCode());
            data=FXCollections.observableList(materiels);
            referenceMateriel.setCellValueFactory(new PropertyValueFactory<>("reference"));
            marqueMAteriel.setCellValueFactory(new PropertyValueFactory<>("marque"));
            DateAchatMAteriel.setCellValueFactory(new PropertyValueFactory<>("date achat"));
            this.materiels.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(Consulter_matérielController.class.getName()).log(Level.SEVERE, null, ex);
        }
 	   
    }    
    @FXML
    private void ConsulterMaterielAction(ActionEvent event) throws IOException {
            utilites.newStage(Deconnexion, "Consulter_matériel.fxml", "consulter materiels");
    }
    @FXML
    private void NouveauTicketAction(ActionEvent event) throws IOException {
            utilites.newStage(Deconnexion, "Ajouter_tickets.fxml" , " nouveau ticket");
    }
    @FXML
    private void DemandeMaterielAction(ActionEvent event) throws IOException {
                utilites.newStage(Deconnexion, "Demande_materiel.fxml"," demande matériel");

    }
    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
                  utilites.newStage(Deconnexion, "login.fxml", "login");
                  Accueil_clientController.utilisateurConnecte=null;

    }
    @FXML
    private void consulterTicketAction(ActionEvent event) throws IOException {
                utilites.newStage(Deconnexion, "Consulter_ticket.fxml"," consulter tickets");
    }
    @FXML
    private void EditerProfileAction(ActionEvent event) throws IOException {
          utilites.newStageWithOldStage("EditerMotDePasse.fxml");
    }
    @FXML
    private void retourAcceuil() throws IOException
    {
    	   utilites.newStage(Deconnexion, "Accueil_client.fxml","consulter tickets");
    	
    }
    @FXML
    private void ConsulterDemandeMaterielAction(ActionEvent event) throws IOException {
                  utilites.newStage(Deconnexion, "ConsulterDemandeMateriel.fxml", "login");

    }
}
