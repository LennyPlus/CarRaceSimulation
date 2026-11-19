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
    
    private CarRaceModel carRaceModel;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        double height = 258;
        
//        carRaceModel = new CarRaceModel(10, trackSizeX, trackSizeY);
        
        // Generate random cars and add them to the pane
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
