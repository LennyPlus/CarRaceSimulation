/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carracesimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author anasi
 */
public class CarRaceModel {
    private static List<CarData> cars = new ArrayList<>();
    private static List<TranslateTransition> transAnimations = new ArrayList<>();
    private static int carAmount = 4;
    
    // Taken from the values shown in scenebuilder
    private static double trackSizeX = 455;
    private static double trackSizeY = 265;
    private static ObservableList<String> winners = FXCollections.observableArrayList();
    
    public CarRaceModel(int carAmount, double width, double height) {
        this.carAmount = carAmount;
//        this.cars = generateRandomCars(height);
        this.trackSizeX = width;
        this.trackSizeY = height;
        initializeTransitions();
    }
    
    public static void initializeCars() {
        cars = generateRandomCars(trackSizeY);
        initializeTransitions();
    }
    
    // Returns a list of random cars
    public static List<CarData> generateRandomCars(double height) {
        List<CarData> carDatas = new ArrayList();
        Random rand = new Random();

        for (int i = 0; i < carAmount; i++) {
            CarData car = new CarData(rand.nextInt(1, 100), rand.nextDouble(50, 100), new Point2D(0, height / (double) carAmount * (i + 0.5)), Color.hsb(rand.nextDouble(0, 360), 1, 1));
            carDatas.add(car);
        }
        
        return carDatas;
    }
    
    
    // Return the shapes of all cars
    public static List<Group> getCarShapes() {
        List<Group> carShapes = new ArrayList<>();
        
        for (CarData carData: cars) {
            carShapes.add(carData.getCarShape());
        }
        
        return carShapes;
    }
    
    public static void initializeTransitions() {
        for (int i = 0; i < carAmount; i++) {
            TranslateTransition transAnim = new TranslateTransition(Duration.seconds(trackSizeY / cars.get(i).getSpeed()), cars.get(i).getCarShape());
            transAnim.setByX(trackSizeX);
            transAnim.setInterpolator(Interpolator.LINEAR);
            
            final int carIdx = i;
            transAnim.setOnFinished(e -> {
                winners.add("Car #" + cars.get(carIdx).getNumber());
            });
            

            transAnimations.add(transAnim);
        }
        
        
        for (int i = 0; i < transAnimations.size(); i++) {
            
        }
    }
    
    public static void moveCars() {
        transAnimations.stream().forEach(TranslateTransition::play);
    }
    
    public static void stopCars() {
        transAnimations.stream().forEach(TranslateTransition::pause);
    }
    
    public static void resetCars() {
        for (int i = 0; i < transAnimations.size(); i++) {
            TranslateTransition anim = transAnimations.get(i);

            // Stop and reset the animation
            anim.stop();
            anim.jumpTo(Duration.ZERO);

            // Reset the car's position
            anim.getNode().setTranslateX(0);
        }
        
        winners.clear();
    }

    public static List<CarData> getCars() {
        return cars;
    }

    public static ObservableList<String> getWinners() {
        return winners;
    }
    
    
    
}
