package com.mycompany.carracesimulation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
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
    
    private static int carAmount = 10;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        double height = 258;
        
        // Generate random cars and add them to the pane
        Random rand = new Random();
        List<Group> carShapes = new ArrayList();
        
        for (int i = 0; i < carAmount; i++) {
            CarData car = new CarData(rand.nextInt(100) + 1, 10, new Point2D(0, height / (double) carAmount * i), Color.CORAL);
            
            carShapes.add(car.getCarShape());
        }
        
        carRacePane.getChildren().addAll(carShapes);
        
    }
    
    

}
