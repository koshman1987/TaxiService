package entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car {
    private int id;
    private AtomicBoolean free = new AtomicBoolean(true);

    public Car(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean isFree() {
        return free;
    }

    public void occupyCar(){
        free.set(false);
        System.out.println("Car with ID " + getId() + " is taken!");

        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void releaseCar(){
        free.set(true);
        System.out.println("Car with ID " + getId() + " is free!");
    }

    @Override
    public String toString(){
        return "Car with ID " + getId();
    }
}