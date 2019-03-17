package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import parser.CarsFileReader;
import parser.CarsParser;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Company {
    private static Company company;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final String FILE_PATH = "./resources/cars.csv";
    private final BlockingQueue<Car> cars;
    private final Iterator<Car> carIterator;
    private static final Logger LOGGER = LogManager.getLogger(Company.class);

    private Company(final BlockingQueue<Car> cars) {
        this.cars = cars;
        this.carIterator = cars.iterator();
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

        while (carIterator.hasNext()) {
            final Car car = carIterator.next();

            if (!customer.isTripDone()) {
                if (!carIterator.hasNext()) {
                    LOGGER.info("No free cars, wait");
                }
                LOGGER.info("Car with ID " + carIterator.next().getId() + " is found for client with ID " + customer.getId());
                car.occupy(customer);
                carIterator.remove();
                LOGGER.info(cars.size() + " cars are available:");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                }

                car.release();
                cars.add(car);
                LOGGER.info(cars.size() + " cars are available");
            }
        }
    }

    private static BlockingQueue<Car> getCars() {
        return new CarsParser().parseCars(new CarsFileReader().parseCarList(FILE_PATH));
    }

    private void showCompanyParkInfo() {
        LOGGER.info(cars.size() + " cars are available");

        for (Car car : cars) {
            LOGGER.info("Car with ID " + car.getId());
        }
    }
}