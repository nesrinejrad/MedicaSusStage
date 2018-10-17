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
import Entities.Fournisseur;
import Entities.Materiel;
import Entities.Role;
import Entities.StatutTicket;
import Entities.TypeMateriel;
import Entities.Utilisateur;
import Services.FournisseurService;
import Services.MaterielService;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterMaterielController implements Initializable {
	@FXML
    private JFXTextField IdentifiantMateriel;
    @FXML
    private JFXTextField DescriptionMateriel;
    @FXML
    private JFXTextField MarqueMateriel;
    @FXML
    private DatePicker DateAchatMateriel;
    @FXML
    private JFXTextField DureeGarantieMateriel;
    @FXML
    private JFXComboBox<String> TypeMaterielC;
    @FXML
    private JFXButton Enregistrer;
    @FXML
    private JFXComboBox<Fournisseur> FournisseurMateriel;
    @FXML
    private JFXButton Enregistrer1;
    @FXML
    private Hyperlink NouveauFournisseur;
    @FXML
    private Label idLabel;
    Utilites utilities= new Utilites();
    private ObservableList<String> typeMterielData=FXCollections.observableArrayList();
    private ObservableList<Fournisseur> fournissursData=FXCollections.observableArrayList();
    MaterielService ms = new MaterielService();
    FournisseurService fs= new FournisseurService();
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	  TypeMateriel[] data=TypeMateriel.values();
     	   for (TypeMateriel typeMateriel : data) {
   		typeMterielData.add(String.valueOf(typeMateriel));
   	}
     	   TypeMaterielC.setItems(typeMterielData);
     	   
     	  
			List<Fournisseur> fournisseurs= new ArrayList<Fournisseur>();
                  try {
                      fournisseurs= fs.displayAll();
                  } catch (SQLException ex) {
                      Logger.getLogger(AjouterMaterielController.class.getName()).log(Level.SEVERE, null, ex);
                  }
			fournissursData=FXCollections.observableArrayList(fournisseurs);
			FournisseurMateriel.setItems(fournissursData);
		} 
     	   
     	   
     	   public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString, formatter);
    return localDate;
}
       
    public void RemplirCahmp(Materiel materiel)
    {	
       IdentifiantMateriel.setText(materiel.getId());
    	IdentifiantMateriel.setVisible(false);
    	idLabel.setVisible(false);
    	//DateAchatMateriel.setValue(materiel.getDateAchat());
    	DescriptionMateriel.setText(materiel.getReference());
    	DureeGarantieMateriel.setText(String.valueOf(materiel.getDureeGarantie()));
    	MarqueMateriel.setText(materiel.getMarque());
    
    }

    @FXML
    private void EnregistrerMateriel(ActionEvent event) throws NamingException, SQLException {
    	Materiel materiel= new Materiel();
    	materiel.setId(IdentifiantMateriel.getText());
    	materiel.setDureeGarantie(Integer.valueOf(DureeGarantieMateriel.getText()));
    	materiel.setFournisseur(String.valueOf(FournisseurMateriel.getValue()));
    	materiel.setMarque(MarqueMateriel.getText());
    	materiel.setReference(DescriptionMateriel.getText());
    	materiel.setTypeMateriel(TypeMateriel.valueOf(TypeMaterielC.getValue()));
    	materiel.setDateAchat(Date.valueOf(DateAchatMateriel.getValue()));
        Materiel m1 =ms.rechercherMateriel(materiel.getId());
        if (m1==null)
        {ms.ajouterMateriel(materiel);
        System.out.println(materiel.toString());
    	utilities.closeStage(Enregistrer);
    	utilities.GenerertAletrtOk("materiel ajouté avec succées");}
        else
        {
            ms.modifierMAteriel(materiel);
        System.out.println(materiel.toString());
    	utilities.closeStage(Enregistrer);
    	utilities.GenerertAletrtOk("materiel ajouté avec succées");}
        
    }

    @FXML
    private void AffecterUtilisateurMAteriel(ActionEvent event) throws NamingException, IOException, SQLException {
    	Materiel materiel= new Materiel();
    	materiel.setId(IdentifiantMateriel.getText());
    	materiel.setDureeGarantie(Integer.valueOf(DureeGarantieMateriel.getText()));
    	materiel.setFournisseur(String.valueOf(FournisseurMateriel.getValue()));
    	materiel.setMarque(MarqueMateriel.getText());
    	materiel.setReference(DescriptionMateriel.getText());
    	materiel.setTypeMateriel(TypeMateriel.valueOf(TypeMaterielC.getValue()));
    	materiel.setDateAchat(Date.valueOf(DateAchatMateriel.getValue()));
        ms.modifierMAteriel(materiel);    
        utilities.closeStage(Enregistrer);
    	AffecterUtilisateurMaterielController affecterUtilisateurMaterielController= new AffecterUtilisateurMaterielController();
    	affecterUtilisateurMaterielController.materiel=materiel;;
    	FXMLLoader loader=new FXMLLoader(getClass().getResource("AffecterUtilisateurMateriel.fxml"));
        Parent root = (Parent) loader.load();
    	affecterUtilisateurMaterielController=loader.getController();
    	affecterUtilisateurMaterielController.RemplirTable(materiel);		
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
    	
    }

    @FXML
    private void NouveauFournisseurAction(ActionEvent event) throws IOException {
    	utilities.newStageWithOldStage("AjouterFournisseur.fxml");
    }
    
}
