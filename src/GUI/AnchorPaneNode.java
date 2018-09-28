package tn.MedicaSud.app.client.gui;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Create an anchor pane that can store additional data.
 */
public class AnchorPaneNode extends AnchorPane {

    // Date associated with this pane
    private LocalDate date;

    /**
     * Create a anchor pane node. Date is not assigned in the constructor.
     * @param children children of the anchor pane
     */
    public AnchorPaneNode(Node... children) {
        super(children);
        // Add action handler for mouse clicked
        this.setOnMouseClicked(e -> System.out.println("This pane's date is: " + date));
        this.setOnMouseClicked(e-> {
            try {
                start(children);
            } catch (IOException ex) {
                Logger.getLogger(AnchorPaneNode.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private void start(Node... children) throws IOException {
         Stage primaryStage= new Stage();
         System.out.println("This pane's date is: " + date);
           
      /*  FXMLLoader loader = new FXMLLoader(getClass().getResource("formulaire.fxml"));
        primaryStage.setTitle("Full Calendar FXML Example");
                primaryStage.setScene(new Scene(loader.load()));

        primaryStage.show();*/
    }
}
