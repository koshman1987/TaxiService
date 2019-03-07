import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        List<Customer> customers = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            customers.add((new Customer((i + 1))));
            customers.get(i).setPosition((int) (Math.random() * 100));
        }

        customers.stream().forEach(customer -> customer.start());
    }
}