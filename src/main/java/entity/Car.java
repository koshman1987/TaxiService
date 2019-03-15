package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class Car {
    private int id;
    private AtomicBoolean free = new AtomicBoolean(true);
    private static final Logger LOGGER = LogManager.getLogger(Car.class);

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
        LOGGER.info(customer + " rides on " + this);
        release();
        customer.setTripIsDone(true);
    }

    private void release() {
        free.set(true);
        LOGGER.info(this + " is free!");
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}