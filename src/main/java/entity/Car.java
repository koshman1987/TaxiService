package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Car {
    private int id;
    private static final Logger LOGGER = LogManager.getLogger(Car.class);

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void occupy(final Customer customer) {
        LOGGER.info("Order for client with ID " + customer.getId() + " is being executed");
        customer.setTripIsDone(true);
    }

    public void release() {
        LOGGER.info("Order is completed. Car with ID " + getId() + "  returned to the parking lot");
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}