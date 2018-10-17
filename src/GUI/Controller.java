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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import Entities.Intervention;
import Services.InterventionServices;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller implements  Initializable {

    // Get the pane to put the calendar on
    @FXML Pane calendarPane;
    
        private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
        private LocalDate calendarDate;
    @FXML
    private BorderPane borderPane;
    @FXML
    private AnchorPane imageMedicaSud;
    @FXML
    private JFXButton Ajouter;
    @FXML
    private JFXButton ListeTickets;
    @FXML
    private JFXButton ListeInterventions;
    @FXML
    private JFXButton Accueil;
    @FXML
    private JFXButton Deconnexion;
    Utilites utilities= new Utilites();
    InterventionServices is= new InterventionServices();
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        	utilities.backgroundImage(imageMedicaSud);

        
         for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            List<Intervention> intervention= new ArrayList<Intervention>();
            List<Integer> periodes= new ArrayList<Integer>();
            try {
				intervention=is.displayAll();
			} catch (SQLException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
            String date2="2018-08-16";
            LocalDate ldate2= LocalDate.parse(date2);
            {
                 System.out.println("hiii");
            }
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
        borderPane.setCenter(calendarPane);
        
    } 

    @FXML
    private void AjouterAction(ActionEvent event) throws IOException {
    	utilities.newStageWithOldStage("AjouterInterventionMateriel.fxml");
    }

    @FXML
    private void ListeTicketsAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "ConsulterTicketAdmin.fxml", "Liste des tickets");
    }

 

    @FXML
    private void ListeInterventionsAction(ActionEvent event) throws IOException {
        
    	utilities.newStage(Accueil, "ListeInterventions.fxml", "liste des interventions"); 
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "AccueilAdmin.fxml", "Accueil");

    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
    	utilities.newStage(Accueil, "login.fxml", "login");

    }
    
}
