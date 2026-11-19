/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.carracesimulation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;

import javafx.scene.layout.Pane;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author anasi
 */
public class SlideshowController implements Initializable {

    @FXML
    private Pane slideshowPane;
    
    private Timeline slideshow;
    private int currentCarIndex = 0;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slideshow = new Timeline(
                new KeyFrame(Duration.seconds(0.5), event -> {
                    showNextCar();
                })
        );

        slideshow.setCycleCount(CarRaceModel.getCars().size());
        
        slideshow.setOnFinished(eh -> {
            try {
                CarRaceFXML.setRoot("primary");
            } catch (IOException ex) {
                System.getLogger(SlideshowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        slideshow.play();
    }    
    
    
    private void showNextCar() {
        slideshowPane.getChildren().clear();

        Group car = CarRaceModel.getCars().get(currentCarIndex).getCarShape();
        
        // Use a group as a container to prevent the transforms of the cars getting messed up
        Group container = new Group(car);
        
        container.setScaleX(5);
        container.setScaleY(5);
        
        container.setTranslateX(slideshowPane.getWidth() / 2);
        container.setTranslateY(slideshowPane.getHeight() / 2 - car.getTranslateY());
        
        slideshowPane.getChildren().add(container);

        currentCarIndex++;
    }
    
}
