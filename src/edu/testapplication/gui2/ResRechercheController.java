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
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author faten
 */
public class ResRechercheController implements Initializable {

    @FXML
    private AnchorPane an;
    @FXML
    private VBox vbox;
    @FXML
    private ListView<?> listeFact;
    @FXML
    private TextField tfstatut;
    @FXML
    private TextField tftype;
    @FXML
    private VBox vbox1;
    @FXML
    private ListView<?> listRes;
    @FXML
    private Button btnShow;
    @FXML
    private Button btnafficher;
    @FXML
    private Label nom;
    @FXML
    private Label numero;
    @FXML
    private Label mail;
    @FXML
    private Label date;
    @FXML
    private Label statut;
    @FXML
    private Label notes;
    @FXML
    private AnchorPane an1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficher(ActionEvent event) {
          List<FormulaireR> formulaire = new ArrayList();
        ServiceFormulaire sf = new ServiceFormulaire();
        listRes.getItems();
        formulaire = sf.recherche_trajet(tftype.getText());
        System.out.println(formulaire);
        int j = 0, t = 0;

        // Set VBox alignment to vertical

        for (FormulaireR fr : formulaire) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(j);
            an.setLayoutY(t);

            Label nom = new Label(fr.getNom());
            nom.setLayoutX(j + 26);
            nom.setLayoutY(t + 14);
            String num = String.valueOf(fr.getTlp());
            Label numero = new Label(num);
            numero.setLayoutX(j + 267);
            numero.setLayoutY(t + 14);
            String u1 = (fr.getMail());
            Label mail = new Label(u1);
            mail.setLayoutX(j + 447);
            mail.setLayoutY(t + 14);
            
//            String u = String.valueOf(c.getV().getDisponibilite());
//            Label user = new Label(u);
//            user.setLayoutX(x + 300);
//            user.setLayoutY(y + 22);
//            String u2 = String.valueOf(c.getV().getNum_entretien());
//            Label user1 = new Label(u2);
//            user1.setLayoutX(x + 420);
//            user1.setLayoutY(y + 22);
//            String u3 = String.valueOf(c.getV().getDate_entretien());
//            Label user3 = new Label(u3);
//            user3.setLayoutX(x + 530);
//            user3.setLayoutY(y + 22);
//            String u4 = String.valueOf(c.getV().getRes_entretien());
//            Label user4 = new Label(u4);
//            user4.setLayoutX(x + 690);
//            user4.setLayoutY(y + 22);
Button btnafficher = new Button("Affichage");
            an.getChildren().addAll(nom, numero, mail);
            vbox.getChildren().add(an);
    }
        
    }
    @FXML
    private void show(ActionEvent event) {
           List<FactureR>  facture = new ArrayList();
        ServiceFacture sfact = new ServiceFacture();
        listeFact.getItems();
        facture = sfact.recherche_statut(tfstatut.getText());
        System.out.println(facture);
        int x = 0, y = 0;

        // Set VBox alignment to vertical

        for (FactureR fr : facture) {

            AnchorPane an1 = new AnchorPane();
            an1.setLayoutX(x);
            an1.setLayoutY(y);

            Label date = new Label(String.valueOf(fr.getDate_Facture()));
            date.setLayoutX(x + 14);
            date.setLayoutY(y + 14);
            
            Label statut = new Label(fr.getStatut());
            statut.setLayoutX(x + 192);
            statut.setLayoutY(y + 14);
            
            Label notes = new Label(fr.getNotes());
            notes.setLayoutX(x + 544);
            notes.setLayoutY(y + 14);
            
//            String u = String.valueOf(c.getV().getDisponibilite());
//            Label user = new Label(u);
//            user.setLayoutX(x + 300);
//            user.setLayoutY(y + 22);
//            String u2 = String.valueOf(c.getV().getNum_entretien());
//            Label user1 = new Label(u2);
//            user1.setLayoutX(x + 420);
//            user1.setLayoutY(y + 22);
//            String u3 = String.valueOf(c.getV().getDate_entretien());
//            Label user3 = new Label(u3);
//            user3.setLayoutX(x + 530);
//            user3.setLayoutY(y + 22);
//            String u4 = String.valueOf(c.getV().getRes_entretien());
//            Label user4 = new Label(u4);
//            user4.setLayoutX(x + 690);
//            user4.setLayoutY(y + 22);
Button btnShow = new Button("Affichage");
            an1.getChildren().addAll(date, statut, notes);
            vbox1.getChildren().add(an1);
    }


    }
    }

    

