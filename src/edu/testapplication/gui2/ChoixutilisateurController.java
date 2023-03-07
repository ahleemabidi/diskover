/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class ChoixutilisateurController implements Initializable {

    @FXML
    private Hyperlink profil;
    @FXML
    private Button homebtn;
    @FXML
    private Button vehicule;
    @FXML
    private Button btnevent;
    @FXML
    private Button btnres;
    @FXML
    private Button rebctn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifyprofil(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("editprofil.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
          Parent signup = FXMLLoader.load(getClass().getResource("home.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

    @FXML
    private void gestionvehicule(ActionEvent event) throws IOException {
           Parent signup = FXMLLoader.load(getClass().getResource("Categorie.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

    @FXML
    private void navigateevent(ActionEvent event) throws IOException {
         Parent signup = FXMLLoader.load(getClass().getResource("Evenement.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

    @FXML
    private void reservation(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("RemplirForm.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

    @FXML
    private void recnavigation(ActionEvent event) throws IOException {
         Parent signup = FXMLLoader.load(getClass().getResource("Reclamation.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }
    
    
}
