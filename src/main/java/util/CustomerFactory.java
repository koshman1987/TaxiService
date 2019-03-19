package util;

import entity.Customer;

public class CustomerFactory {
    public static final Customer create(int id) {
        return new Customer(id);
    }
}
