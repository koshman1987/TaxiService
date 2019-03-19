package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car {
    private int id;
    private Lock lock = new ReentrantLock();
    private AtomicBoolean isFree = new AtomicBoolean(true);
    private static final Logger LOGGER = LogManager.getLogger(Car.class);

    public Car(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void occupy(final Customer customer) {
        lock.lock();

        try {
            LOGGER.info("Order for client with ID " + customer.getId() + " is being executed");
            isFree.set(false);
        } finally {
            lock.unlock();
        }

    }

    public void release(final Customer customer) {
        lock.lock();

        try {
            LOGGER.info("Order is completed. Car with ID " + getId() + "  returned to the parking lot");
            isFree.set(true);
            customer.setTripIsDone(true);
        } finally {
            lock.unlock();
        }
    }

    public AtomicBoolean getState() {
        return isFree;
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}