package utils;

import entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class CustomerPoolGenerator {
    public static List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            customers.add((new Customer((i + 1),  new Semaphore(1))));
            customers.get(i).setXPosition((int)(Math.random() * 100));
        }

        return customers;
    }
}