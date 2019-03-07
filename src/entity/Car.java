package entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class Car {
    private int id;
    private AtomicBoolean free = new AtomicBoolean(true);
    private ReentrantLock lock;

    public static final Logger logger = Logger.getLogger(Car.class.getName());


    public Car(int id, ReentrantLock lock) {
        this.id = id;
        this.lock = lock;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean isFree() {
        return free;
    }

    public void occupy() {
        lock.lock();

        try {
            free.set(false);
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
        } finally {
            lock.unlock();
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