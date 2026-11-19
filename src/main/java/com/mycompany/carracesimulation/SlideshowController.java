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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
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
    private int currentCarIndex = -1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Add welcome message
        Text welcome = new Text(280, 240, "Welcome!");
        welcome.setStyle("-fx-font-size: 30px;");
        
        slideshowPane.getChildren().add(welcome);
            
        
        // Create the MediaPlayer   
        Media media = new Media(
                getClass().getResource("blipSelect.wav").toExternalForm()
        );

        MediaPlayer player = new MediaPlayer(media);

        
        // Create the slideshow
        slideshow = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    showNextCar();
                    
                    player.stop();
                    player.play();
                })
        );

        slideshow.setCycleCount(CarRaceModel.getCars().size() + 2);
        
        slideshow.setOnFinished(eh -> {
            try {
                CarRaceFXML.setRoot("primary");
            } catch (IOException ex) {
                System.getLogger(SlideshowController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        });
        
        slideshow.play();
    }    
    
    
    /**
     * Shows the next car in the race
     */
    private void showNextCar() {
        if (currentCarIndex == -1) {
            currentCarIndex++;
            return;
        }
        
        if (currentCarIndex < CarRaceModel.getCars().size()) {
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
}
