/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.Offre;
import edu.esprit.entities.Promotion;
import edu.esprit.services.IOffreService;
import edu.esprit.services.IPromotionService;
import edu.esprit.services.Serviceoffre;
import edu.esprit.services.Servicepromotion;
import edu.esprit.utils.datasource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class PromotionCrudController implements Initializable {

    @FXML
    private Button btnback;
    @FXML
    private Button deleteoffre;
    @FXML
    private Button modifierroffre;
    @FXML
    private Button ajouteroffre;
    @FXML
    private TextField search;
    @FXML
    private TextField txtAvant;
    @FXML
    private TextField txtnom;
    @FXML
    private TextField txtpour;
    @FXML
    private TextField txtApres;
    @FXML
    private TextField txtido;
    @FXML
    private TextField txtduree1;
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
    private ObservableList<Promotion> PromotionsList;
    private ObservableList<Offre> OffresList;
    private Connection con;
    @FXML
    private TableColumn<Promotion, Integer> idProcolumn;
    @FXML
    private TextArea idtxt;
    @FXML
    private TableView<Offre> tabp;
    @FXML
    private TableColumn<Offre, String> dureeoColumn;
    @FXML
    private TableColumn<Offre, String> descColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          afficherPromotions();
           TableView1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            int idPro = newSelection.getid_promotion();
            try {
                List<Offre> offres = getoffrebyIdPro(idPro);
                ObservableList<Offre> observableOffres = FXCollections.observableArrayList(offres);
                tabp.setItems(observableOffres);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
        }
    });
         
    
    } 
    
    
     void afficherOffres(Promotion p) {
            con=datasource.getInstance().getCnx();
            OffresList = FXCollections.observableArrayList();
         
        
        String query = "SELECT * FROM offre o JOIN promotion p on p.IdO=o.Ido WHERE p.IdO=?  ";
         
        try{
             PreparedStatement st = datasource.getInstance().getCnx().prepareStatement(query);
                    
             st.setInt(1,p.getIdO());
           
             ResultSet rs = st.executeQuery(query);
            while (rs.next()){
                Offre o = new Offre();
                 o.setid_offre(rs.getInt("idO"));
                o.setdescription_offre(rs.getString("DescO"));
                o.setduree_offre(rs.getString("DureeO"));
               
                OffresList.add(o);
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
       
        
         
    dureeoColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_offre()));
    descColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getdescription_offre()));
     

  tabp.setItems(OffresList);
 
  search();
        
        
        
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
  idProcolumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getid_promotion()));
   nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnom_promotion()));
    dureeColumn1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_promotion()));
     prixAVColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_avant()));
      pourColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getpourcentage()));
       prixAPColumn11.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_apres()));
        

  TableView1.setItems(PromotionsList);
 
  search();
 
  }
 
  public void refresh(){
            PromotionsList.clear();
            con=datasource.getInstance().getCnx();
            PromotionsList = FXCollections.observableArrayList();
     
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
  idProcolumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getid_promotion()));
   nomColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getnom_promotion()));
    dureeColumn1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getduree_promotion()));
     prixAVColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_avant()));
      pourColumn.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getpourcentage()));
       prixAPColumn11.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getprix_apres()));
        

  TableView1.setItems(PromotionsList);
  
  search();
  }
    
    
    
    
    
     List<Offre> getoffrebyIdPro(int idPro) throws SQLException{
        List<Offre> arr = new ArrayList<>();
         try {
        PreparedStatement pre = con.prepareStatement("SELECT DescO , DureeO from offre o , promotion p where p.idO=o.idO and p.idPro=?;"); //ORDER BY P asc
         pre.setInt(1, idPro);
         ResultSet rs = pre.executeQuery();

             while(rs.next()){
                     String DescO = rs.getString("DescO");
                       String DureeO = rs.getString("DureeO");
                        
                    
                     Offre m=new Offre(DescO,DureeO);
                    arr.add(m);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return arr;
    }
     private void executeQuery(String query) {
        con = datasource.getInstance().getCnx();
    	Statement st;
    	try {
			st = con.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
     
       @FXML
    private void selectO(MouseEvent event) {
 Promotion promotion = TableView1.getSelectionModel().getSelectedItem();
idtxt.setText("" +promotion.getid_promotion());
txtnom.setText(promotion.getnom_promotion());
txtduree1.setText("" +promotion.getduree_promotion());
txtAvant.setText("" +promotion.getprix_avant());
txtpour.setText("" +promotion.getpourcentage());
txtApres.setText("" +promotion.getprix_apres());
txtido.setText("" +promotion.getIdO());


String id = idtxt.getText();
   String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
         

Promotion p = new Promotion(Integer.parseInt(id),nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));

afficherOffres( p);
    }
    
    @FXML
    private void gomenu(ActionEvent event) throws IOException {
        
         Button btnback = new Button("MENU");    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuAdmin.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }

    @FXML
    private void deleteOffre(ActionEvent event) {
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de modifier!");

        String Value1 = idtxt.getText();
        String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
        
        
       
       
        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
     
         Promotion o= new Promotion(Integer.parseInt(Value1),nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));
        IPromotionService es= new Servicepromotion();
        es.supprimerPromotion(o);
        refresh();
     
        idtxt.setText(null);
        txtduree1.setText(null);
        txtpour.setText(null);
        txtnom.setText(null);
        txtApres.setText(null);
        txtAvant.setText(null);
        txtido.setText(null);
        }
    }

    @FXML
    private void Update(ActionEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de modifier!");

        String Value1 = idtxt.getText();
        String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
        
        
       
       
        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
     
         Promotion o= new Promotion(Integer.parseInt(Value1),nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));
        IPromotionService es= new Servicepromotion();
        es.modifierPromotion(o);
        refresh();
     
        idtxt.setText(null);
        txtduree1.setText(null);
        txtpour.setText(null);
        txtnom.setText(null);
        txtApres.setText(null);
        txtAvant.setText(null);
        txtido.setText(null);
        
        
       
        
        
        } 
        
    }

    @FXML
    private void ajouterOffre(ActionEvent event) {
         
        String duree =txtduree1.getText();
        String pourcentage =txtpour.getText();
          String nom = txtnom.getText();
        String apres =txtApres.getText();
        String avant =txtAvant.getText();
         String ido =txtido.getText();
         
           if( duree.isEmpty() || pourcentage.isEmpty()  || nom.isEmpty()|| pourcentage.isEmpty()  || apres.isEmpty()|| avant.isEmpty()  || ido.isEmpty())
     {
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setContentText("champs vide !!");
     alert.showAndWait();
     
     }   else {
               
   
         Promotion o= new Promotion(nom,Integer.parseInt(duree),Integer.parseInt(avant),Integer.parseInt(pourcentage),Integer.parseInt(apres),Integer.parseInt(ido));
        IPromotionService es= new Servicepromotion();
        es.ajouterPromotion(o);
        refresh();
           
           
           }
         
         
         
         
         
         
         
        
        
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }

   
    
    


    

  private void search() {      
        FilteredList<Promotion>filteredData = new FilteredList<>(PromotionsList, b->true);
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
        SortedList<Promotion>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(TableView1.comparatorProperty());
        TableView1.setItems(sortedData);
    }










}
