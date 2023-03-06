/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.FactureR;
import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFacture;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class ImpayeFactController implements Initializable {

    @FXML
    private DatePicker datef;
    @FXML
    private ChoiceBox<String> bstatut;
        ObservableList<String> choice = FXCollections.observableArrayList("Impayé");

    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btncode;
    @FXML
    private Button btnenregistrer;
    
    String statut = "Impayé";
    @FXML
    private TextField tfnotes;

    
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          bstatut.setItems(choice);
          
        // TODO
    } 
       public String getStatut(){
        return "Impayé";
    } 

    @FXML
    private void directQRcode(ActionEvent event) throws IOException {
          
        String statut = bstatut.getValue();
        System.out.println(statut);
/*
            
        String statut = tfstatut.getText();
        LocalDate date_facture = datef.getValue();
        String notes = tfnotes.getText();
        
        
        FormulaireR selectedFeature = (FormulaireR) listfact.getSelectionModel().getSelectedItem();
        int selectedId = selectedFeature.getId();
        FactureR fact = new FactureR(date_facture, statut, notes, selectedId);
        
        //FormulaireR fr = new FormulaireR();
        //FormulaireR selectedFeature = (FormulaireR) listfact.getSelectionModel().getSelectedItem();
        //int selectedId = selectedFeature.getId();
        
        //FactureR fact = new FactureR(date_facture, statut, notes, fr);
        ServiceFacture sfact = new ServiceFacture();
        sfact.ajouter(fact);
*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ImpayeQRcode.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
        LocalDate date_Facture = datef.getValue();
        String notes = tfnotes.getText();
        String satut = bstatut.getValue();
        
        ServiceFacture sfact = new ServiceFacture();
        InfoReservationController irc = new InfoReservationController();
        FormulaireR form = irc.getFr();
        //int id = form.getId();
                
      FactureR fact = new FactureR(Date.valueOf(date_Facture), statut, notes, form);
     
        sfact.ajouter(fact);
        System.out.println("Facture ajouté !");
        
        
    }

    

 
    
    
    
  /*  public String getStatut() {
        return statut;
    }*/

    @FXML
    private void ajouter(ActionEvent event) {
    }
    
}
