package entity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Customer extends Thread {
    private long id;
    private int position;
    private boolean tripIsDone;
    private Lock lock;


    public Customer(int id, Lock lock) {
        this.id = id;
        this.lock = lock;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public void run() {
        Company.placeOrder(this);
    }

    public void setTripIsDone(boolean tripIsDone) {
        this.tripIsDone = tripIsDone;
    }

    public boolean isTripDone() {
        return tripIsDone;
    }

    @Override
    public String toString() {
        return "Client with ID " + getId();
    }
}