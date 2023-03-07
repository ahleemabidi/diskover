/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class MenuAdminController implements Initializable {

    @FXML
    private Button btnOffreA;
    @FXML
    private Button btnPromoA;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        // TODO
    }    

    @FXML
    private void ofrreAdmin(ActionEvent event) throws IOException {
               Button btnOffreA = new Button("OffreBack");    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("OffreFXML.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void promoAdmin(ActionEvent event) throws IOException {
                       Button btnPromoA = new Button("PromBack");    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PromotionCrud.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

}
