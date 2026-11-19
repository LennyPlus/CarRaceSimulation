package com.mycompany.carracesimulation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        speedSlider.valueProperty().addListener((o, ov, nv) -> {
            CarRaceModel.changeTransitionSpeeds(nv.doubleValue());
            speedLabel.setText(String.format("%.2f", nv.doubleValue()));
        });
        
        
        resultsListView.setItems(CarRaceModel.getWinners());
        
        
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
    }
}
