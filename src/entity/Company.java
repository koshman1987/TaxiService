package entity;

import utils.CarPoolGenerator;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Company {
    private List<Car> cars;
    private static Company company = null;
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);

    private Company() {
        cars = CarPoolGenerator.getCars();
    }

    public static Company getCompany() {

        if (company == null && !instanceCreated.get()) {
            company = new Company();
            instanceCreated.set(true);
        }

        return company;
    }

    public List<Car> getCars() {
        return cars;
    }
}
