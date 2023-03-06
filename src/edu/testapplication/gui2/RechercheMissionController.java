/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Mission;
import edu.esprit.services.ServiceMission;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class RechercheMissionController implements Initializable {

    @FXML
    private Button prev;
   
    private String Matricule;
    @FXML
    private Label matrciule;
    @FXML
    private Label description;
    @FXML
    private Label heuredebut;
    @FXML
    private Label heurefin;
    @FXML
    private ScrollPane scroll;
    @FXML
    private AnchorPane ann;
    @FXML
    private VBox vboxx;
    @FXML
    private ListView<Mission> listee;

    public void setMatricule(String Matricule) {
        this.Matricule = Matricule;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionMission.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void afficherMission(ActionEvent event) {
        List<Mission> vehicule = new ArrayList();
        ServiceMission sm = new ServiceMission();
        listee.getItems();
        vehicule = sm.recherche(Matricule);
        System.out.println(vehicule);
        int x = 0, y = 0;

        // Set VBox alignment to vertical
        for (Mission c : vehicule) {

            AnchorPane ann = new AnchorPane();
            ann.setLayoutX(x);
            ann.setLayoutY(y);

            Label matricule = new Label(c.getMatricule());
            matricule.setLayoutX(x + 20);
            matricule.setLayoutY(y + 22);
            String d = String.valueOf(c.getDescription());
            Label description = new Label(d);
            description.setLayoutX(x + 100);
            description.setLayoutY(y + 22);
            String u1 = String.valueOf(c.getHeure_debut());
            Label heuredebut = new Label(u1);
            heuredebut.setLayoutX(x + 190);
            heuredebut.setLayoutY(y + 22);
            String u2 = String.valueOf(c.getHeure_fin());
            Label heurefin = new Label(u2);
            heurefin.setLayoutX(x + 320);
            heurefin.setLayoutY(y + 22);

            ann.getChildren().addAll(matricule, description, heuredebut, heurefin);
            vboxx.getChildren().add(ann);
        }
    }

}
