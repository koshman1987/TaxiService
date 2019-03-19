package service;

import entity.Car;
import entity.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public class CarService {
    private static final Logger LOGGER = LogManager.getLogger(Car.class);

    public void occupy(final Customer customer, final Car car) {
        LOGGER.info("Order for client with ID " + customer.getId() + " is being executed");
        car.setAvailability(new AtomicBoolean(false));
    }

    public void release(final Customer customer, final Car car) {
        LOGGER.info("Order is completed. Car with ID " + car.getId() + " returned to the parking lot");
        car.setAvailability(new AtomicBoolean(true));
    }
}
