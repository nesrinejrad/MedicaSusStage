/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import tn.MedicaSud.services.DemandeServicesRemote;
import tn.MedicaSud.services.FournisseurServicesRemote;
import tn.MedicaSud.services.InterventionServicesRemote;
import tn.MedicaSud.services.MaterielServicesRemote;
import tn.MedicaSud.services.PanneServicesRemote;
import tn.MedicaSud.services.TicketSerciesRemote;
import tn.MedicaSud.services.UtilisateurServicesRemote;

/**
 *
 * @author USER
 */
public class Utilites {
    public static UtilisateurServicesRemote utilisateurServicesRemote;
    public static TicketSerciesRemote ticketSerciesRemote;
    public static DemandeServicesRemote demandeServicesRemote;
    static MaterielServicesRemote materielServicesRemote;
    static PanneServicesRemote panneServicesRemote;
    static InterventionServicesRemote interventionServicesRemote;
    static FournisseurServicesRemote fournisseurServicesRemote;
    static String FournisseurRemote="MedicaSud-ear/MedicaSud-service/FournisseurServices!tn.MedicaSud.services.FournisseurServicesRemote";
    public static String utilRemote="MedicaSud-ear/MedicaSud-service/UtilisateurServices!tn.MedicaSud.services.UtilisateurServicesRemote";
    static String panneRemote="MedicaSud-ear/MedicaSud-service/PanneServices!tn.MedicaSud.services.PanneServicesRemote";
    static String ticketRemote="MedicaSud-ear/MedicaSud-service/TicketSercies!tn.MedicaSud.services.TicketSerciesRemote";
    static String demandeRemote="MedicaSud-ear/MedicaSud-service/DemandeServices!tn.MedicaSud.services.DemandeServicesRemote";
    static String materielRemote="MedicaSud-ear/MedicaSud-service/MaterielServices!tn.MedicaSud.services.MaterielServicesRemote";
    public static Context context;
    static String interventionRemote="MedicaSud-ear/MedicaSud-service/InterventionServices!tn.MedicaSud.services.InterventionServicesRemote";
    public void newStage(JFXButton button , String fxml , String Title) throws IOException
    {
         FXMLLoader loader=new FXMLLoader(getClass().getResource(fxml));
         Parent root = (Parent) loader.load();		
         Scene newScene = new Scene(root);
         Stage newStage = new Stage();
         Stage stage = (Stage) button.getScene().getWindow();
         newStage.setHeight(stage.getHeight());
         newStage.setTitle(Title);
         newStage.setWidth(stage.getWidth());
         newStage.setScene(newScene);
         newStage.show();
         // do what you have to do
         stage.close();
    }
    public void newStageAdmin(AnchorPane anchropane , String fxml , String Title) throws IOException
    {
         FXMLLoader loader=new FXMLLoader(getClass().getResource(fxml));
         Parent root = (Parent) loader.load();		
         Scene newScene = new Scene(root);
         Stage newStage = new Stage();
         Stage stage = (Stage) anchropane.getScene().getWindow();
         newStage.setHeight(stage.getHeight());
         newStage.setTitle(Title);
         newStage.setWidth(stage.getWidth());
         newStage.setScene(newScene);
         newStage.show();
         // do what you have to do
         stage.close();
    }
    
    public void closeStage(JFXButton button)
    {
        
         Stage stage = (Stage) button.getScene().getWindow();
         // do what you have to do
         stage.close();
    }
   public void newStageWithOldStage(String fxml) throws IOException
   {
        FXMLLoader loader=new FXMLLoader(getClass().getResource(fxml));
         Parent root = (Parent) loader.load();		
         Scene newScene = new Scene(root);
         Stage newStage = new Stage();
         // newStage.setHeight(300);
        //newStage.setWidth(450);
         newStage.setScene(newScene);
         newStage.show();
   }
    
    public void backgroundImage(AnchorPane anchorPane)
    {
            BackgroundImage myBI= new BackgroundImage(new Image("Assets/MSI-Group.jpg",200,80,false,true),
        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
          BackgroundSize.DEFAULT);
        //then you set to your node
        anchorPane.setBackground(new Background(myBI));
    }
    
    public static void GenererAlerte(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(msg);
        alert.showAndWait(); 
    }
    
    public static void GenerertAletrtOk(String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succes");
        alert.setContentText(msg);
        alert.showAndWait(); 
    }

  
}
