package entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car {
    private int id;
    private Semaphore semaphore;
    private AtomicBoolean isFree = new AtomicBoolean(true);

    public Car(int id, final Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean checkAvailability() {
        return isFree;
    }

    public void setAvailability(final AtomicBoolean isFree) {
        this.isFree = isFree;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    @Override
    public String toString() {
        return "Car with ID " + getId();
    }
}