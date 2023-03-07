/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author abidi
 */
public class ApiSmsController implements Initializable {

    @FXML
    private TextField smsText;

    @FXML
    private TextField numText;

    @FXML
    private Button btnEnvoyer;
     @FXML
    private Button btnRetour;

 
    

    
    public void sendMessage(String message,String numero){
    Twilio.init("ACf08d1bcd88962f492de0135480183899", "45ca8e7e796cf077533702c4e928bc5f");
      Message msg = Message.creator(new PhoneNumber(numero), new PhoneNumber("+12706790747"), message).create();
        System.out.println("twilio");
        System.out.println(msg.getSid());
 


    }
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    @FXML
    void envoyerSMS(ActionEvent event) {
        String message = smsText.getText();
        String numero = numText.getText();
        sendMessage(message, numero);

    }
       @FXML
    void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Back.fxml"));
        Parent root = loader.load();
        Scene newScene = new Scene(root);
        Stage stage = (Stage) btnRetour.getScene().getWindow();
        stage.setScene(newScene);
        stage.show();

    }
}