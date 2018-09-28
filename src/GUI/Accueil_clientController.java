/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import tn.MedicaSud.app.client.gui.ConsulterDemandeMaterielController;
import tn.MedicaSud.app.client.gui.Utilites;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Accueil_clientController implements Initializable {

   
     Utilites utilites= new Utilites();
  
   
    @FXML
    private TableColumn<?, ?> referenceMateriel;
    @FXML
    private TableColumn<?, ?> marqueMAteriel;
    @FXML
    private TableColumn<?, ?> DateAchatMAteriel;
    
    static Utilisateur utilisateurConnecte;
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private AnchorPane ConsulterMateriel;
    @FXML
    private ImageView ImgConsulterMateriel;
    @FXML
    private AnchorPane ConsulterTickets;
    @FXML
    private ImageView ImgConsulterTickets;
    @FXML
    private AnchorPane NouveauTicket;
    @FXML
    private ImageView ImgNouveauTicket;
    @FXML
    private AnchorPane NouveauMateriel;
    @FXML
    private ImageView ImgNouveauMateriel;
    @FXML
    private AnchorPane ConsulterDemande;
    @FXML
    private ImageView ImgConsulterDemande;
    @FXML
    private AnchorPane ChangerMotDePasse;
    @FXML
    private ImageView ImgChangerMotDePasse;
    @FXML
    private AnchorPane Deconnexion;
    @FXML
    private ImageView imgDeconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilites.backgroundImage(imageMedicaSud);
          
     	   Image img = new Image("Assets/icons8-voir-les-détails-50.png");
     	   ImgConsulterTickets.setImage(img);
     	   
     	    img = new Image("Assets/icons8-poste-de-travail-64.png");
   	   ImgConsulterMateriel.setImage(img);
   	   
   	   img = new Image("Assets/icons8-ajouter-32.png");
   	   ImgNouveauTicket.setImage(img);
   	   
   	   img = new Image("Assets/demandeblanche.png");
   	   ImgNouveauMateriel.setImage(img);;
   	   
   	   img = new Image("Assets/icons8-editer-le-fichier-80.png");
   	   ImgChangerMotDePasse.setImage(img);
   	   
   	   img = new Image("Assets/icons8-connexion-filled-50.png");
   	   imgDeconnexion.setImage(img);
    }    

    @FXML
    private void ConsulterMaterielAction(MouseEvent event) throws IOException {
    	utilites.newStageAdmin(ChangerMotDePasse, "Consulter_matériel.fxml", "liste matériel");
    }

    @FXML
    private void ConsulterTicketsAction(MouseEvent event) throws IOException {
    	utilites.newStageAdmin(ChangerMotDePasse, "Consulter_ticket.fxml", "liste tickets");
    }

    @FXML
    private void NouveauTicketAction(MouseEvent event) throws IOException {

    	utilites.newStageAdmin(ChangerMotDePasse, "Ajouter_tickets.fxml", "Ajouter ticket");
    
    }

    @FXML
    private void NouveauMaterielAction(MouseEvent event) throws IOException {

    	utilites.newStageAdmin(ChangerMotDePasse, "Demande_materiel.fxml", "Demande materiel");
    
    }

    @FXML
    private void ConsulterDemandeAction(MouseEvent event) throws IOException {
    	ConsulterDemandeMaterielController consulterDemandeMaterielController= new ConsulterDemandeMaterielController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("ConsulterDemandeMateriel.fxml"));
        Parent root = (Parent) loader.load();
    	consulterDemandeMaterielController=loader.getController();
    	consulterDemandeMaterielController.remplirDemandeUtilisateur();		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        Stage stage = (Stage) ChangerMotDePasse.getScene().getWindow();
        stage.close();
        newStage.show();
        this.initialize(null, null);
    }

    @FXML
    private void ChangerMotDePasseAtion(MouseEvent event) throws IOException {
    	utilites.newStageWithOldStage("EditerMotDePasse.fxml");
    }

    @FXML
    private void DeconnexionAction(MouseEvent event) throws IOException {
    	utilites.newStageAdmin(ChangerMotDePasse, "login.fxml", "login");
    	utilisateurConnecte=null;
    }   
    /**
     * Initializes the controller class.
     */

    }    
   
  
   

 
    
