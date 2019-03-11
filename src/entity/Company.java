package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Company {
    private static Company company = null;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final Logger logger = Logger.getLogger(Company.class.getName());
    private static Iterator<Car> iterator;

    private Company() {
    }

    public static Company getCompany() {
        List<Car> cars = new ArrayList<>();
        lock.lock();

        try {
            if (!instanceCreated.get()) {
                company = new Company();
                instanceCreated.set(true);
            }

            for (int i = 0; i < 10; i++) {
                cars.add((new Car((int) (Math.random() * 100))));
            }

            iterator = Collections.synchronizedCollection(cars).iterator();
            return company;
        } finally {
            lock.unlock();
        }
    }

    public static void placeOrder(Customer customer) {
        logger.log(Level.INFO, customer.toString() + " placed an order!");
        Company.processOrder(customer);
    }

    private static void processOrder(Customer customer) {
        while (iterator.hasNext()) {
            if (iterator.next().isFree().get()) {
                Car car = iterator.next();

                if (!customer.isTripDone()) {
                    try {
                        car.occupy(customer);
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        logger.log(Level.WARNING, e.getMessage());
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        getCompany();

        for (int i = 1; i < 6; i++) {
            new Customer(i).start();
        }
    }
}