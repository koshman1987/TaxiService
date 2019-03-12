package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Company {
    private static Company company;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final int COUNT_OF_CARS = 10;
    private List<Car> cars = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(Company.class.getName());

    private Company() {
    }

    {
        for (int i = 0; i < COUNT_OF_CARS; i++) {
            cars.add((new Car((int) (Math.random() * 100))));
        }
    }

    public static Company getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();

            try {
                company = new Company();
                instanceCreated.set(true);
            } finally {
                lock.unlock();
            }
        }
        return company;
    }

    public void placeOrder(Customer customer) {
        logger.log(Level.INFO, customer.toString() + " placed an order!");
        processOrder(customer);
    }

    private void processOrder(Customer customer) {
        for (Car car : cars) {
            if (car.isFree().get()) {
                if (!customer.isTripDone()) {
                    car.occupy(customer);
                }
            }
        }
    }
}