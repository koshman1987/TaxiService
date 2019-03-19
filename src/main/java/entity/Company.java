package entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import service.CarService;

import util.CarsFileReader;
import util.CarsParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Company {
    private static volatile Company instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static final String FILE_PATH = "./resources/cars.csv";
    private final List<Car> cars;
    private CarService carService = new CarService();
    private List<Customer> servedCustomers = Collections.synchronizedList(new ArrayList<>());
    private static final Logger LOGGER = LogManager.getLogger(Company.class);

    private Company(final List<Car> cars) {
        this.cars = cars;
        showCompanyParkInfo();
    }

    public static Company getInstance() {
        if (!instanceCreated.get()) {
            if (instance == null) {
                lock.lock();

                try {
                    if (instance == null) {
                        instance = new Company(getCars());
                        instanceCreated.set(true);
                    }
                } finally {
                    lock.unlock();
                }

            }
        }

        return instance;
    }

    public void placeOrder(final Customer customer) {
        LOGGER.info(customer + " placed an order!");
        processOrder(customer);
    }

    protected void processOrder(final Customer customer) {
        LOGGER.info("The company took the order from client with ID " + customer.getId());
        LOGGER.info("Searching for an available car for client with ID " + customer.getId() + " ...");

        for (final Car car : cars) {
            if (car.checkAvailability().get() && !servedCustomers.contains(customer)) {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    car.getSemaphore().acquire();
                    lock.lock();

                    try {
                        LOGGER.info("Car with ID " + car.getId() + " is found for client with ID " + customer.getId());
                        carService.occupy(customer, car);
                    } finally {
                        lock.unlock();
                    }

                    carService.release(customer, car);
                    servedCustomers.add(customer);
                    car.getSemaphore().release();
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
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