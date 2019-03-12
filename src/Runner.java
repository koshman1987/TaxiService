import entity.Customer;

public class Runner {
    private static final int COUNT_OF_CUSTOMERS = 6;

    public static void main(String[] args) {
        for (int i = 1; i < COUNT_OF_CUSTOMERS; i++) {
            new Customer(i).start();
        }
    }
}