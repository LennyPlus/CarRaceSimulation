/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carracesimulation;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

/**
 *
 * @author anasi
 */
public class CarData {
    private int number;
    private double speed;
    // Position along the race track, no need for y coordinates
    private double position;
    private Color color;

    public CarData(int number, double speed, double position, Color color) {
        this.number = number;
        this.speed = speed;
        this.position = position;
        this.color = color;
    }
    
    
    public List<Shape> getCarShape() {
        List<Shape> shapes = new ArrayList<>();
        
        shapes.add(new Polygon(1,1, 5,5, 10, 10, 0, 0));
        shapes.add(new Circle(5, 0, 0));
        shapes.add(new Circle(5, 5, 0));
        shapes.add(new Text(String.valueOf(number)));
        
        return shapes;
    }
    
    
}
