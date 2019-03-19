package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import parser.CarsFileReader;
import parser.CarsParser;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Company {
    private static Company company;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final String FILE_PATH = "./resources/cars.csv";
    private final List<Car> cars;
    private static final Logger LOGGER = LogManager.getLogger(Company.class);

    private Company(final List<Car> cars) {
        this.cars = cars;
        showCompanyParkInfo();
    }

    public static Company getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();

            try {
                if (company == null) {
                    company = new Company(getCars());
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }

        return company;
    }

    public void placeOrder(final Customer customer) {
        LOGGER.info(customer + " placed an order!");
        processOrder(customer);
    }

    private void processOrder(final Customer customer) {
        LOGGER.info("The company took the order from client with ID " + customer.getId());
        LOGGER.info("Searching for an available car for client with ID " + customer.getId() + " ...");

        for (Car car : cars) {
            if (!customer.isTripDone() && car.getState().get()) {
                lock.lock();

                try {
                    LOGGER.info("Car with ID " + car.getId() + " is found for client with ID " + customer.getId());
                    car.occupy(customer);
                } finally {
                    car.release(customer);
                    lock.unlock();
                }

            }
        }
    }

    private static List<Car> getCars() {
        return new CarsParser().parseCars(new CarsFileReader().parseCarList(FILE_PATH));
    }

    private void showCompanyParkInfo() {
        LOGGER.info(cars.size() + " cars are available:");

        for (Car car : cars) {
            LOGGER.info("Car with ID " + car.getId());
        }
    }
}