package util;

import entity.Customer;

public class CustomerFactory {
    public static final Customer create(final int id) {
        return new Customer(id);
    }
}
