/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.carracesimulation;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
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
    
    
    public Group getCarShape() {
//        List<Shape> shapes = new ArrayList<>();
        Group shapes = new Group();
        Shape poly = new Polygon(0.0, 0.0,
                20.0, 10.0,
                10.0, 20.0);
        Shape wheel1 = new Circle(0, 10, 5);
        Shape wheel2 = new Circle(20, 10, 5);
        Shape text = new Text(String.valueOf(number));
        text.setTranslateY(10);

        poly.setFill(Color.RED);
        wheel1.setFill(Color.BLACK);
        wheel2.setFill(Color.BLACK);
        
        shapes.getChildren().add(poly);
        shapes.getChildren().add(wheel1);
        shapes.getChildren().add(wheel2);
        shapes.getChildren().add(text);

        return shapes;
    }
    
}
