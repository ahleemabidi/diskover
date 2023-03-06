/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.entities.FactureR;
import edu.esprit.entities.FormulaireR;
import edu.esprit.services.ServiceFacture;
import edu.esprit.services.ServiceFormulaire;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class ResAdminController implements Initializable {

    @FXML
    private ListView<?> listReservation;
    @FXML
    private Label nom;
    @FXML
    private Label tlp;
    @FXML
    private Label mail;
    @FXML
    private Label type;
    @FXML
    private Label categ;
    @FXML
    private Label depart;
    @FXML
    private Label destination;
    @FXML
    private Label nbr;
    @FXML
    private Label opt;
    @FXML
    private VBox vbox;
    private AnchorPane an;
    @FXML
    private AnchorPane ev;
    @FXML
    private ListView<?> listFact;
    @FXML
    private Label datef;
    @FXML
    private Label statutf;
    @FXML
    private Label labelf;
    @FXML
    private VBox vbox1;
    @FXML
    private AnchorPane ev1;
    @FXML
    private Button accederbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        FormulaireR fr = new FormulaireR(); 
        System.out.println(fr.toString());
        List<FormulaireR> Res = new ArrayList();
        ServiceFormulaire sf = new ServiceFormulaire();
        
        listReservation.getItems();
        Res = sf.getAll();
        System.out.println(an);
        int x = 0, y = 0;
        
        // listevent.getItems().addAll(even);
        //Res.add(this.fr);
        for (FormulaireR e : Res) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label nom = new Label(e.getNom());
            nom.setLayoutX(x + 7);
            nom.setLayoutY(y + 6);
            String num = String.valueOf(e.getTlp());
            Label numero = new Label(num);
            numero.setLayoutX(x + 58);
            numero.setLayoutY(y + 6);
            Label mail = new Label(e.getMail());
            mail.setLayoutX(x + 127);
            mail.setLayoutY(y + 6);
            Label type = new Label(e.getType());
            type.setLayoutX(x + 284);
            type.setLayoutY(y + 6);
            Label categorie = new Label(e.getCateg());
            categorie.setLayoutX(x + 350);
            categorie.setLayoutY(y + 6);
            Label depart = new Label(e.getDepart());
            depart.setLayoutX(x + 452);
            depart.setLayoutY(y + 6);
            Label destination = new Label(e.getDestination());
            destination.setLayoutX(x + 561);
            destination.setLayoutY(y + 6);
            String nbr = String.valueOf(e.getNbr());
            Label nombre = new Label(nbr);
            nombre.setLayoutX(x + 663);
            nombre.setLayoutY(y + 6);
            Label option = new Label(e.getOpt());
            option.setLayoutX(x + 706);
            option.setLayoutY(y + 7);

            //Button btnafficher = new Button("Affichage");

            an.getChildren().addAll(nom, numero, mail, type, categorie, depart, destination, nombre, option);
            ev.getChildren().addAll(an);

            vbox.getChildren().add(an);
            
            
             FactureR fc = new FactureR(); 
        System.out.println(fr.toString());
        List<FactureR> facture= new ArrayList();
        ServiceFacture sfc = new ServiceFacture();
        
        listReservation.getItems();
        facture = sfc.getAll();
        System.out.println(an);
        int j = 0, t = 0;
        
        // listevent.getItems().addAll(even);
        //Res.add(this.fr);
        for (FactureR f : facture) {

            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(j);
            an1.setLayoutY(t);

            Label datef = new Label(String.valueOf(f.getDate_Facture()));
            datef.setLayoutX(j + 8);
            datef.setLayoutY(t + 14);
            String statut= (f.getStatut());
            Label statutf = new Label(statut);
            statutf.setLayoutX(j + 232);
            statutf.setLayoutY(t + 14);
            Label notesf = new Label(f.getNotes());
            notesf.setLayoutX(x + 475);
            notesf.setLayoutY(y + 14);
            

            //Button btnafficher = new Button("Affichage");

            an1.getChildren().addAll(datef, statutf, notesf);
            ev1.getChildren().addAll(an1);

            vbox1.getChildren().add(an1);
   
        }
        }       
        // TODO
    }    

    @FXML
    private void acceder(ActionEvent event) throws IOException {
        Parent signup = FXMLLoader.load(getClass().getResource("ResRecherche.fxml")) ; 
        Scene signupscene = new Scene (signup) ; 
        Stage appStage= (Stage)((Node)event.getSource()).getScene().getWindow() ; 
    appStage.setScene(signupscene) ; 
    appStage.show() ;
    }
    
    }
   

