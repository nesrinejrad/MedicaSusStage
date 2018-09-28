/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.MedicaSud.app.client.gui;

import java.io.IOException;
import java.time.YearMonth;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author USER
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
    	FXMLLoader loader= new FXMLLoader(getClass().getResource("login.fxml"));
        //FXMLLoader loader= new FXMLLoader(getClass().getResource("AccueilAdmin.fxml"));

        Parent root = loader.load();                           
        Scene scene= new Scene(root);     
        primaryStage.setHeight(600);
        primaryStage.setWidth(750);
        primaryStage.setTitle("login");
        primaryStage.setScene(scene);
        primaryStage.show();
    	
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
