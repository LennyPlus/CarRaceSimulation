/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carracesimulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;

/**
 *
 * @author anasi
 */
public class CarRaceModel {
    private List<CarData> cars;
    private int carAmount = 10;
    
    
    public CarRaceModel(int carAmount,double height) {
        this.cars = generateRandomCars(carAmount, height);
    }
    
    // Returns a list of random cars
    public static List<CarData> generateRandomCars(int carAmount, double height) {
        List<CarData> carDatas = new ArrayList();
        Random rand = new Random();

        for (int i = 0; i < carAmount; i++) {
            CarData car = new CarData(rand.nextInt(1, 100), 10, new Point2D(0, height / (double) carAmount * i), Color.CORAL);
//            CarData car = new CarData(rand.nextInt(1, 100), 10, new Point2D(0, 100), Color.CORAL);

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
}
