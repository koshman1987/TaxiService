package entity;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Customer extends Thread {
    private long id;
    private int position;
    private Company company;

    public static final Logger logger = Logger.getLogger(Customer.class.getName());

    public Customer(int id) {
        this.id = id;
        this.company = Company.getCompany();
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

    public void callTaxi() {
        logger.info("Client with ID " + getId() + " calls taxi!");

        try {
            for (Car car : company.getCars()) {

                if (car.isFree().get()) {
                    car.occupy();
                    logger.info("Car with ID " + car.getId() + " is taken by client with ID " + getId());
                    logger.info("Client with ID " + getId() + " rides on car with ID " + car.getId());
                    TimeUnit.SECONDS.sleep(3);
                    car.release();
                    return;
                }

                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public void run() {
        callTaxi();
    }

    @Override
    public String toString() {
        return "Client with ID " + getId();
    }
}