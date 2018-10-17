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
import Entities.Panne;
import Entities.Utilisateur;
import Services.PanneServices;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class GestionPannesController implements Initializable {
	@FXML
    private AnchorPane imageMedicaSud;
	@FXML
    private TableColumn<Panne, String> CodePanne;
    @FXML
    private TableColumn<Panne, String> DescriptionPanne;
    @FXML
    private TableColumn<Panne, LocalDate> SolutionPanne;
    @FXML
    private TableColumn<Panne, String>TypeMateriel;

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
    private TableView<Panne> PanneTable;
    private ObservableList<Panne> data;
    PanneServices ps = new PanneServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	utilies.backgroundImage(imageMedicaSud);
    	List<Panne> pannes= new ArrayList<Panne>();
   	   try {
		pannes=ps.displayAll();
		data=FXCollections.observableList(pannes);	
		CodePanne.setCellValueFactory(new PropertyValueFactory<>("id"));
	   	  DescriptionPanne.setCellValueFactory(new PropertyValueFactory<>("description"));
	   	  TypeMateriel.setCellValueFactory(new PropertyValueFactory<>("typeMateriel"));
	   	  SolutionPanne.setCellValueFactory(new PropertyValueFactory<>("solution"));
	   	  this.PanneTable.setItems(data);
   	   } catch (SQLException ex) { 	
                Logger.getLogger(GestionPannesController.class.getName()).log(Level.SEVERE, null, ex);
            } 	
    }    

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilies.newStageWithOldStage("Nouvelle_Pannes.fxml");
    }

    @FXML
    private void modifierAction(ActionEvent event) throws IOException {
      	Panne panne= PanneTable.getSelectionModel().getSelectedItem();
    	Nouvelle_PannesController nouvelle_PannesController= new Nouvelle_PannesController();
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("Nouvelle_Pannes.fxml"));
        Parent root = (Parent) loader.load();
    	nouvelle_PannesController=loader.getController();
    	nouvelle_PannesController.RemplirCahmp(panne);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
        this.initialize(null, null);
    	

    }

    @FXML
    private void supprimerAction(ActionEvent event) throws NamingException {
            try {
                Panne panne= new Panne();
                panne=PanneTable.getSelectionModel().getSelectedItem();
                ps.supprimerPanne(panne);
                utilies.GenerertAletrtOk("panne supprimée avec succes");
                this.initialize(null, null);
            } catch (SQLException ex) {
                Logger.getLogger(GestionPannesController.class.getName()).log(Level.SEVERE, null, ex);
            }
    	
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
