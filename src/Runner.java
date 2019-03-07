import entity.Customer;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Runner {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Runner.class.getName());

        for (int i = 0; i < 10; i++) {
            new Customer((i + 1)).start();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                logger.warning(e.getMessage());
            }
        }

    }
}