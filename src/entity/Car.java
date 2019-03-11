package entity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Car {
    private int id;
    private AtomicBoolean free = new AtomicBoolean(true);
    private static final Logger logger = Logger.getLogger(Car.class.getName());

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean isFree() {
        return free;
    }

    public void occupy(Customer customer) {
        free.set(false);
        logger.log(Level.INFO, customer.toString() + " rides on " + toString());
        customer.setTripIsDone(true);
        release();
    }

    private void release() {
        free.set(true);
        logger.log(Level.INFO, toString() + " is free!");
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}