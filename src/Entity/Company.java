package Entity;

import utils.CarPoolGenerator;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Company {
    private List<Car> carPool ;
    private static Company company = null;

    private Company(){
        carPool = CarPoolGenerator.getCars();
    }

    public static synchronized Company getCompany(){

        if(company == null){
            company = new Company();
        }

        return company;
    }

    public List<Car> getCarPool() {
        return carPool;
    }
}
