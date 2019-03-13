package entity;

import parser.CarsFileReader;
import parser.CarsParser;

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
    private static AtomicBoolean carsCreated = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private List<Car> cars = new ArrayList<>();
    private static final String FILE_PATH = "C:\\Users\\Viachaslau_Koshman\\IdeaProjects\\TaxiService\\cars.csv";
    private static final Logger LOGGER = Logger.getLogger(Company.class.getName());

    private Company() {
    }

    public static Company getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();

            try {
                company = new Company();

                if (!carsCreated.get()) {
                    company.prepareCars();
                }

                instanceCreated.set(true);
                carsCreated.set(true);
            } finally {
                lock.unlock();
            }
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