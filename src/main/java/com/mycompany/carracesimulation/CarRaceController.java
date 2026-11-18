package com.mycompany.carracesimulation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarData car = new CarData(10, 10, 0, Color.CORAL);
        
        Group group = car.getCarShape();
        group.setTranslateY(100);
        
        carRacePane.getChildren().addAll(group);
        
    }
    
    

}
