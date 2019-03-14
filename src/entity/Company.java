package entity;

import parser.CarsFileReader;
import parser.CarsParser;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Company {
    private static Company company;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private static final String FILE_PATH = "cars.csv";
    private static final List<Car> CARS = new CarsParser().getCars(new CarsFileReader().parseCarList(FILE_PATH));
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());

    private Company() {
    }

    public static Company getInstance() {
        lock.lock();

        try {
            if (!Company.instanceCreated.get()) {
                company = new Company();
                Company.instanceCreated.getAndSet(true);
            }
        } finally {
            lock.unlock();
        }

        return company;
    }

    public void placeOrder(final Customer customer) {
        LOGGER.log(Level.INFO, customer + " placed an order!");
        processOrder(customer);
    }

    private void processOrder(final Customer customer) {
        for (Car car : CARS) {
            if (car.isFree().get()) {
                if (!customer.isTripDone()) {
                    car.occupy(customer);
                }
            }
        }
    }
}