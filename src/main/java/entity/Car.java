package entity;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car {
    private int id;
    private Semaphore semaphore;
    private AtomicBoolean isFree = new AtomicBoolean(true);

    public Car(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    public int getId() {
        return id;
    }

    public AtomicBoolean getState() {
        return isFree;
    }

    public void setState(AtomicBoolean isFree) {
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