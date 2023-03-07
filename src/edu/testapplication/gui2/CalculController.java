/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.testapplication.gui2;

import edu.esprit.services.ServiceMission;
import edu.esprit.utils.datasource;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Map;
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
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class CalculController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private Button calculbtn;
    @FXML
    private Button btnprev;
    @FXML
    private Label res;
    @FXML
    private AnchorPane an;
    @FXML
    private PieChart pie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void calcul(ActionEvent event) {
        String matricule = id.getText();
        ServiceMission sm = new ServiceMission();
        Map<Date, Integer> dureesParDate = sm.calculer_duree_mission_mat(matricule);
        afficherPieChart(dureesParDate);

    }

    @FXML
    private void prev(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GestionMission.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    private void afficherPieChart(Map<Date, Integer> dureesParDate) {
int totalDuree = 0;
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map.Entry<Date, Integer> entry : dureesParDate.entrySet()) {
            Date date = entry.getKey();
            int duree = entry.getValue();
            totalDuree += duree;
            int dureeRestante = 3600 - duree;
            dataset.setValue(date.toString(), duree);
            dataset.setValue(date.toString() + " (restante)", dureeRestante);
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Mission Durations", // chart title
                dataset, // data
                true, // include legend
                true, // tooltips
                false // urls
        );

        Color realDurationColor = new Color(0, 128, 0); // Vert foncé
        Color remainingDurationColor = new Color(255, 0, 0); // Rouge
        PiePlot piePlot = (PiePlot) chart.getPlot();

        piePlot.setSectionPaint("Durée réelle", realDurationColor);
        piePlot.setSectionPaint("Durée restante", remainingDurationColor);
        ChartPanel chartPanel = new ChartPanel(chart);

// Display the chart
        JFrame frame = new JFrame("Pie Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);

    }
    

}
