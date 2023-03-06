/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFormulaire;
import edu.esprit.services.Serviceclient;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class InfoReservationController implements Initializable {

    @FXML
    private ListView<String> listRes;
    @FXML
    private ListView<String> listchauff;
    @FXML
    private ScrollPane scres;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btnafficher;
    @FXML
    private Button btnannuler;
    @FXML
    private Button btnmodifier;
    
    FormulaireR fr = new FormulaireR();
    @FXML
    private Button btnconfirmerr;
    @FXML
    private VBox vbox2;
    @FXML
    private AnchorPane an2;
    @FXML
    private Button reserverbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        // TODO
    }

    @FXML
    private void Afficher(ActionEvent event) {
        
        
        System.out.println(fr.toString());
        List<FormulaireR> Res = new ArrayList();
        ServiceFormulaire sf = new ServiceFormulaire();
        FormulaireR fr = new FormulaireR();
        listRes.getItems();
        //Res = sf.getOnebyId(fr.getId());
        System.out.println(an);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        Res.add(this.fr);
        for (FormulaireR e : Res) {

            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(x);
            an1.setLayoutY(y);

            Label nom = new Label(e.getNom());
            nom.setLayoutX(x + 7);
            nom.setLayoutY(y + 4);
            String num = String.valueOf(e.getTlp());
            Label numero = new Label(num);
            numero.setLayoutX(x + 77);
            numero.setLayoutY(y + 4);
            Label mail = new Label(e.getMail());
            mail.setLayoutX(x + 165);
            mail.setLayoutY(y + 4);
            Label type = new Label(e.getType());
            type.setLayoutX(x + 384);
            type.setLayoutY(y + 4);
            Label categorie = new Label(e.getCateg());
            categorie.setLayoutX(x + 592);
            categorie.setLayoutY(y + 4);
            Label depart = new Label(e.getDepart());
            depart.setLayoutX(x + 742);
            depart.setLayoutY(y + 4);
            Label destination = new Label(e.getDestination());
            destination.setLayoutX(x + 832);
            destination.setLayoutY(y + 4);
            String nbr = String.valueOf(e.getNbr());
            Label nombre = new Label(nbr);
            nombre.setLayoutX(x + 943);
            nombre.setLayoutY(y + 4);
            Label option = new Label(e.getOpt());
            option.setLayoutX(x + 1037);
            option.setLayoutY(y + 4);

            Button btnafficher = new Button("Affichage");

            an.getChildren().addAll(nom, numero, mail, type, categorie, depart, destination, nombre, option);
            an.getChildren().addAll(an1);

            vbox.getChildren().add(an1);

        }

    }

    @FXML
    private void modification(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifResrvation.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void AnnulerRes(ActionEvent event) {

        FormulaireR fr = new FormulaireR();
        ServiceFormulaire sform = new ServiceFormulaire();
        sform.supprimer(Integer.valueOf(listRes.getId()));

    }

    private void confirmer(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModalitePaiement.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
public void setFormulaire(FormulaireR fr){
        this.fr = fr ;
        }

    public FormulaireR getFr() {
        return fr;
    }

    @FXML
    private void confirmerr(ActionEvent event) {
        vbox2.getChildren().clear();  
        List<edu.esprit.entities.Client> c = new ArrayList();
        Serviceclient sc = new Serviceclient();
       // listchauff.getItems();
        c = sc.getchauffeurs();
        Client c1= new Client() ; 
        System.out.println(c);
        //FormulaireR fr = new FormulaireR();
        
        //Res = sf.getOnebyId(fr.getId());
        
        int j = 0, t = 0;
        
        // listevent.getItems().addAll(even);
       // c.add(this.fr);
        for (edu.esprit.entities.Client e : c) {

            AnchorPane an3 = new AnchorPane();
            an3.setLayoutX(j);
            an3.setLayoutY(t);

           
         
            Label nom = new Label(e.getNom());
            nom.setLayoutX(j + 49);
            nom.setLayoutY(t + 14);
            Label prenom = new Label(e.getPrenom());
            prenom.setLayoutX(j + 315);
            prenom.setLayoutY(t + 14);
            
            Label email = new Label(e.getEmail());
            email.setLayoutX(j + 695);
            email.setLayoutY(t + 14);
            


           // Button btnafficher = new Button("Affichage");
            
            an3.getChildren().addAll(nom, prenom,email);
            
            an2.getChildren().addAll(an3);

            vbox2.getChildren().add(an3);
        // TODO
    }    

    }
//    List<edu.esprit.entities.Client> c = new ArrayList();
//        Serviceclient sc = new Serviceclient();
//        listRes.getItems();
//        c = sc.getall();
//        System.out.println(c);
//        //FormulaireR fr = new FormulaireR();
//        
//        //Res = sf.getOnebyId(fr.getId());
//        
//        int x = 0, y = 0;
//        // listevent.getItems().addAll(even);
//       // c.add(this.fr);
//        for (edu.esprit.entities.Client e : c) {
//
//            AnchorPane an = new AnchorPane();
//            an.setLayoutX(x);
//            an.setLayoutY(y);
//
//            Label cin = new Label(e.getCin());
//            cin.setLayoutX(x + 4);
//            cin.setLayoutY(y + 14);
//         
//            Label nom = new Label(e.getNom());
//            nom.setLayoutX(x + 81);
//            nom.setLayoutY(y + 14);
//            Label prenom = new Label(e.getPrenom());
//            prenom.setLayoutX(x + 163);
//            prenom.setLayoutY(y + 14);
//            Label role = new Label(e.getRole());
//            role.setLayoutX(x + 253);
//            role.setLayoutY(y + 14);
//            Label email = new Label(e.getEmail());
//            email.setLayoutX(x + 332);
//            email.setLayoutY(y + 14);
//            Label pwd = new Label(e.getPwd());
//            pwd.setLayoutX(x + 547);
//            pwd.setLayoutY(y + 14);
//
//
//           // Button btnafficher = new Button("Affichage");
//
//            an.getChildren().addAll(cin, nom, prenom, role, email, pwd);
//       ev.getChildren().addAll(an);
//
//            vbox.getChildren().add(an);
//        // TODO
//    }    

    @FXML
    private void reservernavigation(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("reserverchauffeur.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }

}
