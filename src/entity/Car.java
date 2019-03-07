package entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class Car {
    private int id;
    private AtomicBoolean free = new AtomicBoolean(true);

    public static final Logger logger = Logger.getLogger(Car.class.getName());


    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean isFree() {
        return free;
    }

    public void occupy() {
        free.set(false);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void release() {
        free.set(true);
        logger.info("Car with ID " + getId() + " is free!");
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}