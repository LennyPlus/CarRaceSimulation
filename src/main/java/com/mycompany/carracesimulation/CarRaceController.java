package com.mycompany.carracesimulation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author anasi
 */
public class CarRaceController implements Initializable{
    @FXML
    private Pane carRacePane;
    
    @FXML
    private ListView<String> resultsListView;
    
    @FXML
    private Slider speedSlider;
    
    @FXML
    private Label speedLabel;
    
    @FXML
    private Label raceFinishedLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        speedSlider.valueProperty().addListener((o, ov, nv) -> {
            CarRaceModel.changeTransitionSpeeds(nv.doubleValue());
            speedLabel.setText(String.format("%.2f", nv.doubleValue()));
        });
        
        
        resultsListView.setItems(CarRaceModel.getWinners());
        resultsListView.getItems().addListener(
                (ListChangeListener<String>) change -> {
                    raceFinishedLabel.setVisible(true);
                });
        
        
        // Add the cars to the pane
        carRacePane.getChildren().addAll(CarRaceModel.getCarShapes());
        
    }
    
    
    @FXML
    void onStarButtonPressed(ActionEvent event) {
        CarRaceModel.moveCars();
    }
    
    @FXML
    void onPauseButtonPressed(ActionEvent event) {
        CarRaceModel.stopCars();
    }

    @FXML
    void onResetButtonPressed(ActionEvent event) {
        CarRaceModel.resetCars();
        raceFinishedLabel.setVisible(false);
    }
}
