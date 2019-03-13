package entity;

import parser.CarsFileReader;
import parser.CarsParser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class Company {
    private static Company company;
    private static Lock lock = new ReentrantLock();
    private List<Car> cars = new ArrayList<>();
    private static final String FILE_PATH = "cars.csv";
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());

    private Company() {
    }

    public static Company getInstance() {
        lock.lock();

        try {
            if (company == null) {
                company = new Company();
                company.prepareCars();
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
        for (Car car : cars) {
            if (car.isFree().get()) {
                if (!customer.isTripDone()) {
                    car.occupy(customer);
                }
            }
        }
    }

    private void prepareCars() {
        cars = CarsParser.getInstance().getCars(CarsFileReader.getInstance().parseCarList(FILE_PATH));
    }
}