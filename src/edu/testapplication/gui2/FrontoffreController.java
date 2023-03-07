/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Offre;
import edu.esprit.utils.datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
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
public class FrontoffreController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private TableView<Offre> TableViewFrontoffre;
    @FXML
    private TableColumn<Offre, String> descColumn;
    @FXML
    private TableColumn<Offre, String> dureeColumn;
    @FXML
    private TextField search;

    ObservableList<Offre>offrelist;
    Connection con;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherOffres();
         search();
    }    

    
    void afficherOffres(){
            con=datasource.getInstance().getCnx();
            offrelist = FXCollections.observableArrayList();
     
      try {
            String requete = "SELECT * FROM offre o ";
            Statement st = datasource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Offre o = new Offre();
                o.setid_offre(rs.getInt("idO"));
                o.setdescription_offre(rs.getString("DescO"));
                o.setduree_offre(rs.getString("DureeO"));
                
                
                offrelist.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  
  descColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getdescription_offre()));
  dureeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getduree_offre()));
  
 
  TableViewFrontoffre.setItems(offrelist);
 search();
 
 
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
    private void selectO(MouseEvent event) {
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }
    
    private void search() {      
        FilteredList<edu.esprit.entities.Offre>filteredData = new FilteredList<>(offrelist, b->true);
        search.textProperty().addListener((observable, oldValue, newValue)->{
            filteredData.setPredicate(Offre->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 if(String.valueOf(Offre.getduree_offre()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(Offre.getdescription_offre()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<edu.esprit.entities.Offre>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableViewFrontoffre.comparatorProperty());
        TableViewFrontoffre.setItems(sortedData);
    }
    
    
    
}
