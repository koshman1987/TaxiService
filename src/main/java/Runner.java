import entity.Company;

import util.CustomerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Runner {
    private static final int COUNT_OF_CUSTOMERS = 5;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 1; i < COUNT_OF_CUSTOMERS; i++) {
            service.submit(new Runnable() {
                public void run() {
                    Company.getInstance().placeOrder(CustomerFactory.create(((int) (Math.random() * 100 + 1))));
                }
            });
        }

    }
}