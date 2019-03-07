package utils;

import entity.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class CarPoolGenerator {
    private static ReentrantLock lock = new ReentrantLock();

    public static List<Car> getCars() {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            cars.add((new Car((int) (Math.random() * 100), lock)));
        }

        return cars;
    }
}
