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
    private static double trackSizeY = 240;
    private static ObservableList<String> winners = FXCollections.observableArrayList();
    private static boolean isRaceStarted = false;
    
    public CarRaceModel(int carAmount, double width, double height) {
        this.carAmount = carAmount;
        this.trackSizeX = width;
        this.trackSizeY = height;
        initializeTransitions();
    }
    
    /**
     * Initializes the cars variable as well as their transitions
     */
    public static void initializeCars() {
        cars = generateRandomCars(trackSizeY);
        initializeTransitions();
    }
    
    /**
     * Generates random cars
     * @param height Height of the race track, used to determine the spacing between cars
     * @return An ArrayList that contains random cars
     */
    public static List<CarData> generateRandomCars(double height) {
        List<CarData> carDatas = new ArrayList();
        Random rand = new Random();

        for (int i = 0; i < carAmount; i++) {
            CarData car = new CarData(rand.nextInt(1, 100), rand.nextDouble(20, 50), new Point2D(0, height / (double) carAmount * (i + 0.5)), Color.hsb(rand.nextDouble(0, 360), 1, 1));
            carDatas.add(car);
        }
        
        return carDatas;
    }
    
    
    /**
     * Returns an array list that contains the shapes of all the cars
     * @return An array list that contains the shapes of all the cars
     */
    public static List<Group> getCarShapes() {
        List<Group> carShapes = new ArrayList<>();
        
        for (CarData carData: cars) {
            carShapes.add(carData.getCarShape());
        }
        
        return carShapes;
    }
    
    /**
     * Initializes the TranslateTransitions used by the cars to simulate movement
     */
    public static void initializeTransitions() {
        for (int i = 0; i < carAmount; i++) {
            TranslateTransition transAnim = new TranslateTransition(Duration.seconds(trackSizeY / cars.get(i).getSpeed()), cars.get(i).getCarShape());
            transAnim.setByX(trackSizeX);
            transAnim.setInterpolator(Interpolator.LINEAR);
            
            final int carIdx = i;
            transAnim.setOnFinished(e -> {
                winners.add((winners.size() + 1) + ": Car #" + cars.get(carIdx).getNumber());
            });
            
            transAnimations.add(transAnim);
        }
        
        
        for (int i = 0; i < transAnimations.size(); i++) {
            
        }
    }
    
    /**
     * Changes the rate of the TranslateTransitions
     * @param newSpeed The new rate of the transitions
     */
    public static void changeTransitionSpeeds(double newSpeed) {
        transAnimations.forEach(anim -> {
            anim.setRate(newSpeed);
            
            if (isRaceStarted) {
                if (anim.getStatus() != Animation.Status.STOPPED) {
                    anim.play();
                }
            }
        });
    }
    
    /**
     * Starts the transition for each car
     */
    public static void moveCars() {
        transAnimations.forEach(anim -> {
            if (anim.getNode().getTranslateX() != trackSizeX) {
                anim.play();
            }
            });
        isRaceStarted = true;
    }
    
    /**
     * Pauses the transition for each car
     */
    public static void stopCars() {
        transAnimations.forEach(TranslateTransition::pause);
        isRaceStarted = false;
    }
    
    /**
     * Resets the transition and position for each car
     */
    public static void resetCars() {
        isRaceStarted = false;
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
