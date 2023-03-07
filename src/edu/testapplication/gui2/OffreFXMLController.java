/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Offre;
import edu.esprit.services.IOffreService;
import edu.esprit.services.Serviceoffre;
import edu.esprit.utils.datasource;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Hp
 */
public class OffreFXMLController implements Initializable {

    @FXML
    private TextField txtduree;
    @FXML
    private TextField txtdesc;
    @FXML
    private TextField txtid;
    @FXML
    private Button btnback;
    @FXML
    private TableView<Offre> TableviewOffre;
    @FXML
    private TableColumn<Offre, Integer> idoffre;
    private TableColumn<Offre, String> dureeColumn;
    private TableColumn<Offre, String> descColumn1;
    @FXML
    private Button supprimerOffre;
    @FXML
    private Button modifierOffre;
    @FXML
    private Button ajouterOffre;
    @FXML
    private TextField search;
    private ObservableList<Offre> OffreList;
    private Connection con;
    @FXML
    private TableColumn<Offre, String> description;
    @FXML
    private TableColumn<Offre,String > duree;
    Connection mc;
    PreparedStatement ste;
    @FXML
    private Button buttonpdf;
    
     com.mysql.jdbc.Connection cnx = datasource.getInstance().getCnx() ;
     
      PreparedStatement ps=null;
        ResultSet rs=null;
    @FXML
    private Button triiii;
    @FXML
    private VBox idpromo;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherOffres();
    }    
    
    
    
    
    
   
   void afficherOffres(){
            con=datasource.getInstance().getCnx();
            OffreList = FXCollections.observableArrayList();
     
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
                
                
                OffreList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  idoffre.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getid_offre()));
  description.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getdescription_offre()));
  duree.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getduree_offre()));
  
 
  TableviewOffre.setItems(OffreList);
 
  refresh();
  search();
 
  }
 
  public void refresh(){
            OffreList.clear();
            con=datasource.getInstance().getCnx();
            OffreList = FXCollections.observableArrayList();
     
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
               
                OffreList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        TableviewOffre.setItems(OffreList);
        search();
       
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
    private void selected(MouseEvent event) {
        
        Offre clicked = TableviewOffre.getSelectionModel().getSelectedItem();
        txtid.setText(String.valueOf(clicked.getid_offre()));
        txtduree.setText(String.valueOf(clicked.getduree_offre()));
        txtdesc.setText(String.valueOf(clicked.getdescription_offre()));
    
    }

    @FXML
    private void deleteOffre(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de supprimer!");

        String Value1 = txtid.getText();
        String rate =txtduree.getText();
        String description =txtdesc.getText();
        
       
       
        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
     
         Offre o= new Offre(Integer.parseInt(Value1),rate,description);
        IOffreService es= new Serviceoffre();
        es.supprimerOffre(o);
        refresh();
     
        txtid.setText(null);
        txtduree.setText(null);
        txtdesc.setText(null);
        
        
        } 
    }

    @FXML
    private void updateOffre(MouseEvent event) { Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
       alert.setHeaderText("Warning");
       alert.setContentText("Es-tu sûre de modifier!");

        String Value1 = txtid.getText();
        String duree =txtduree.getText();
        String description =txtdesc.getText();
        
       
       
        Optional<ButtonType>result =  alert.showAndWait();
        if(result.get() == ButtonType.OK)
        {
     
         Offre o= new Offre(Integer.parseInt(Value1),duree,description);
        IOffreService es= new Serviceoffre();
        es.modifierOffre(o);
        refresh();
     
        txtid.setText(null);
        txtduree.setText(null);
        txtdesc.setText(null);
        
        
        } 
    }

    @FXML
    private void addOffre(MouseEvent event) {
        
        
     
      
     
     
//  String id = txtid.getText();
     String duree = txtduree.getText();
     String description = txtdesc.getText();
    
     if(  duree.isEmpty()  || description.isEmpty())
     {
     Alert alert = new Alert(Alert.AlertType.ERROR);
     alert.setContentText("champs vide !!");
     alert.showAndWait();
     
     }   
     
     
     else {
     
     
     
    Offre o = new Offre(1,duree,description);
    IOffreService os = new Serviceoffre();
    os.ajouterOffre(o);
    Notifications.create().title("Succeess").text("l'offre est ajoutee").showInformation();
        
        
        refresh();
     }
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
        
    }

    @FXML
    private void addpdf(MouseEvent event) throws DocumentException, IOException, SQLException {
      
 String querry="SELECT * FROM offre";

            ps=cnx.prepareStatement(querry);
            rs=ps.executeQuery();
        
        
  
    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesOffres.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" *************************************** Liste Des Offres *************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    PdfPCell cell;
//titre de la colonne
    cell = new PdfPCell(new Phrase("Description", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
   
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Duree", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
   
    table.addCell(cell);
   
   

    while (rs.next()) {

        Offre e = new Offre();
     
        e.setdescription_offre(rs.getString("DescO"));
        e.setduree_offre(rs.getString("DureeO"));
      
     
        cell = new PdfPCell(new Phrase(e.getdescription_offre(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
       
        cell = new PdfPCell(new Phrase(e.getduree_offre(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
       
      
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesOffres.pdf"));
    }

    @FXML
    private void triisignaaal(MouseEvent event) {
             con=datasource.getInstance().getCnx();
            OffreList = FXCollections.observableArrayList();
     
      try {
            String requete = "SELECT * FROM offre o ORDER BY DescO DESC";
            Statement st = datasource.getInstance().getCnx()
                    .createStatement();
            ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                Offre o = new Offre();
                o.setid_offre(rs.getInt("idO"));
                o.setdescription_offre(rs.getString("DescO"));
                o.setduree_offre(rs.getString("DureeO"));
                
                
                OffreList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
  idoffre.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getid_offre()));
  description.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getdescription_offre()));
  duree.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getduree_offre()));
  
 
  TableviewOffre.setItems(OffreList);
 
    }

    
    
    
    
    
    private void search() {      
        FilteredList<edu.esprit.entities.Offre>filteredData = new FilteredList<>(OffreList, b->true);
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
        sortedData.comparatorProperty().bind(TableviewOffre.comparatorProperty());
        TableviewOffre.setItems(sortedData);
    }

    @FXML
    private void promonavigation(MouseEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("PromotionCrud.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
          
    }
    
    
    }
    



