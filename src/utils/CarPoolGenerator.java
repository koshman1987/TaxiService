package utils;

import Entity.Car;

import java.util.ArrayList;
import java.util.List;

public class CarPoolGenerator {
    public static List<Car> getCars(){
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            cars.add((new Car((int)(Math.random() * 100))));
        }

        return cars;
    }
}
