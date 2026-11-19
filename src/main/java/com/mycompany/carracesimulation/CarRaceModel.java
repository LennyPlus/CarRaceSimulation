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
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author anasi
 */
public class CarRaceModel {
    private List<CarData> cars;
    private List<TranslateTransition> transAnimations = new ArrayList<>();
    private int carAmount = 10;
    private double trackSizeY = 0;
    private double trackSizeX = 0;
    private CarData winner;
    
    public CarRaceModel(int carAmount, double width, double height) {
        this.carAmount = carAmount;
        this.cars = generateRandomCars(height);
        this.trackSizeX = width;
        this.trackSizeY = height;
        initializeTransitions();
    }
    
    // Returns a list of random cars
    public List<CarData> generateRandomCars(double height) {
        List<CarData> carDatas = new ArrayList();
        Random rand = new Random();

        for (int i = 0; i < carAmount; i++) {
            CarData car = new CarData(rand.nextInt(1, 100), rand.nextDouble(50, 100), new Point2D(0, height / (double) carAmount * i), Color.hsb(rand.nextDouble(0, 360), 1, 1));
            carDatas.add(car);
        }
        
        return carDatas;
    }
    
    
    // Return the shapes of all cars
    public List<Group> getCarShapes() {
        List<Group> carShapes = new ArrayList<>();
        
        for (CarData carData: cars) {
            carShapes.add(carData.getCarShape());
        }
        
        return carShapes;
    }
    
    public void initializeTransitions() {
        for (int i = 0; i < carAmount; i++) {
            TranslateTransition transAnim = new TranslateTransition(Duration.seconds(trackSizeY / cars.get(i).getSpeed()), cars.get(i).getCarShape());
            transAnim.setByX(trackSizeX);
            transAnim.setInterpolator(Interpolator.LINEAR);
            
            final int carIdx = i;
            transAnim.setOnFinished(e -> {
                if (winner == null) {
                    winner = cars.get(carIdx);
                }
            });
            

            transAnimations.add(transAnim);
        }
        
        
        for (int i = 0; i < transAnimations.size(); i++) {
            
        }
    }
    
    public void moveCars() {
        transAnimations.stream().forEach(TranslateTransition::play);
    }
    
    public void stopCars() {
        transAnimations.stream().forEach(TranslateTransition::pause);
    }
    
    public void resetCars() {
        for (int i = 0; i < transAnimations.size(); i++) {
            TranslateTransition anim = transAnimations.get(i);

            // Stop and reset the animation
            anim.stop();
            anim.jumpTo(Duration.ZERO);

            // Reset the car's position
            anim.getNode().setTranslateX(0);
        }
        
        winner = null;
    }
    
}
