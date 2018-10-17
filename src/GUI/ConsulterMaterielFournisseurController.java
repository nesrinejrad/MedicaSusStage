/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Fournisseur;
import Entities.Materiel;
import Services.MaterielService;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConsulterMaterielFournisseurController implements Initializable {

    @FXML
    private TableView<Materiel> materiels;
    @FXML
    private TableColumn<Materiel, String> CodeMateriel;
    @FXML
    private TableColumn<Materiel, String> referenceMateriel;
    @FXML
    private TableColumn<Materiel, String> marqueMAteriel;
    @FXML
    private TableColumn<Materiel, LocalDate > DateAchatMAteriel;
    @FXML
    private TableColumn<Materiel, Integer> DureeGarantie;
    private ObservableList<Materiel> data;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void remplirTableau(Fournisseur fournisseur) throws SQLException
    {   MaterielService ms= new MaterielService();
    	List<Materiel> materiels= new ArrayList<Materiel>();
            materiels=ms.displayAllMaterielParournisseur(fournisseur.getId());
   	//   materiels.addAll(fournisseur.getMateriels());
   	   data=FXCollections.observableList(materiels);		
   	  referenceMateriel.setCellValueFactory(new PropertyValueFactory<>("reference"));
   	  marqueMAteriel.setCellValueFactory(new PropertyValueFactory<>("marque"));
   	  DateAchatMAteriel.setCellValueFactory(new PropertyValueFactory<>("date achat"));
   	  DureeGarantie.setCellValueFactory(new PropertyValueFactory<>("dureeGarantie"));
 	  CodeMateriel.setCellValueFactory(new PropertyValueFactory<>("id"));
   	  this.materiels.setItems(data);
   
    }
    
}
