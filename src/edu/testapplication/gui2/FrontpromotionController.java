/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Promotion;
import edu.esprit.utils.datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class FrontpromotionController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private TextField search;
    @FXML
    private TableView<Promotion> TableView1;
    @FXML
    private TableColumn<Promotion, String> nomColumn;
    @FXML
    private TableColumn<Promotion, Integer> dureeColumn1;
    @FXML
    private TableColumn<Promotion, Integer> prixAVColumn;
    @FXML
    private TableColumn<Promotion, Integer> pourColumn;
    @FXML
    private TableColumn<Promotion, Integer> prixAPColumn11;

   Connection con;
   ObservableList<Promotion> PromotionsList;
    
    
    /**
     * Initializes the controller class.
     */
   
    public void initialize(URL url, ResourceBundle rb) {
       afficherPromotions();
    }    

    
    
    
    void afficherPromotions(){
            con=datasource.getInstance().getCnx();
            PromotionsList = FXCollections.observableArrayList();
     
      try {
            String requete = "SELECT * FROM promotion o ";
            Statement st = datasource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Promotion o = new Promotion();
                o.setid_promotion(rs.getInt("IdPro"));
                o.setnom_promotion(rs.getString("nomP"));
               o.setduree_promotion(rs.getInt("dureeP"));
               o.setprix_avant(rs.getInt("PrixAvant"));
               o.setpourcentage(rs.getInt("Pourcentage"));
               o.setprix_apres(rs.getInt("PrixApres"));
               o.setIdO(rs.getInt("idO"));
                
                
                PromotionsList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 
   nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnom_promotion()));
    dureeColumn1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_promotion()));
     prixAVColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_avant()));
      pourColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getpourcentage()));
       prixAPColumn11.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_apres()));
        

  TableView1.setItems(PromotionsList);
 
   search();
  }
    
    
     private void search() {      
        FilteredList<edu.esprit.entities.Promotion>filteredData = new FilteredList<>(PromotionsList, b->true);
        search.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(Promotion->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 if(String.valueOf(Promotion.getnom_promotion()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(Promotion.getpourcentage()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<edu.esprit.entities.Promotion>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableView1.comparatorProperty());
        TableView1.setItems(sortedData);
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void gomenu(ActionEvent event) throws IOException {
                 Button btnback = new Button("MENU");    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuFXML.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }

    @FXML
    private void selectO(MouseEvent event) {
    }
    
}
