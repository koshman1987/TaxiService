package entity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Car {
    private int id;
    private AtomicBoolean free = new AtomicBoolean(true);
    private static final Logger LOGGER = Logger.getLogger(Car.class.getName());

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean isFree() {
        return free;
    }

    public void occupy(final Customer customer) {
        free.set(false);
        LOGGER.log(Level.INFO, customer.toString() + " rides on " + this.toString());
        release();
        customer.setTripIsDone(true);
    }

    private void release() {
        free.set(true);
        LOGGER.log(Level.INFO, this.toString() + " is free!");
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}