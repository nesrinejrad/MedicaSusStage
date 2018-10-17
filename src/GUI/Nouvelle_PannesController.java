/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import Entities.Panne;
import Entities.TypeMateriel;
import Services.PanneServices;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Nouvelle_PannesController implements Initializable {

    @FXML
    private JFXButton ValiderNouvellePanne;
    Utilites utilites= new Utilites();
    @FXML
    private JFXComboBox<String> Type;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField solutionText;
    @FXML
    private Label  Solution;
    private ObservableList<String> dateTypeMateriel=FXCollections.observableArrayList();
    static int dim1;
    static int dim2;
    @FXML
    private Label idLabel;
    boolean modif=false;
    PanneServices ps = new PanneServices();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	idLabel.setVisible(false);
        TypeMateriel[] typeMateriels=TypeMateriel.values();
        for (TypeMateriel typeMateriel : typeMateriels) {
        	dateTypeMateriel.add(String.valueOf(typeMateriel));
			System.out.println(String.valueOf(typeMateriel));
		}
        Type.setItems(dateTypeMateriel);
        System.out.println("dim1="+dim1);
        System.out.println("dim2="+dim2);
        Solution.setVisible(true);
        solutionText.setVisible(true);

    }    

    @FXML
    private void ValiderNouvellePanneAction(ActionEvent event) throws NamingException, IOException {
		 Panne panne= new Panne();
		 if(modif==true)
		 {
			 panne.setId(Integer.valueOf(idLabel.getText()));
		 
                 
		 panne.setTypeMateriel(TypeMateriel.valueOf(Type.getValue()));
		 panne.setDescription(description.getText());
		 if(solutionText!=null)
		 {
			 panne.setSolution(solutionText.getText());
                         ps.modifierPanne(panne);
                         utilites.closeStage(ValiderNouvellePanne);
			 
		 }}
		 else
		 {  panne.setTypeMateriel(TypeMateriel.valueOf(Type.getValue()));
		 panne.setDescription(description.getText());
                     			 panne.setSolution(solutionText.getText());

                     ps.ajouterPAnne(panne);
	     utilites.closeStage(ValiderNouvellePanne);
	    /* FXMLLoader loader= new FXMLLoader(getClass().getResource("Ajouter_tickets.fxml"));
	     Stage primaryStage= new Stage();
	        Parent root = loader.load();                           
	        Scene scene= new Scene(root);     
	        primaryStage.setHeight(dim2);
	        primaryStage.setWidth(dim1);
	        primaryStage.setTitle("Ajout ticket");
	        primaryStage.setScene(scene);
	        primaryStage.show();*/}
	     
    }
    
    public void NouvellePanneUser()
    {	
    	Solution.setVisible(false);
    	solutionText.setVisible(false);
    }
    public void RemplirCahmp(Panne panne)
    {	modif=true;
    	idLabel.setText(String.valueOf(panne.getId()));
    	description.setText(panne.getDescription());
    	solutionText.setText(panne.getSolution());
    }
}
