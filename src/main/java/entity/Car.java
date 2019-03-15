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
        LOGGER.info("Order for client with ID " + customer.getId() + " is being executed");
        release();
        customer.setTripIsDone(true);
    }

    private void release() {
        free.set(true);
        LOGGER.info("Order is completed. Car with ID " + getId() + "  returned to the parking lot");
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}