package entity;

import utils.CarPoolGenerator;

import java.util.List;

public class Company {
    private List<Car> cars;
    private static Company company = null;

    private Company() {
        cars = CarPoolGenerator.getCars();
    }

    public static Company getCompany() {

        if (company == null) {
            company = new Company();
        }

        return company;
    }

    public List<Car> getCars() {
        return cars;
    }
}
