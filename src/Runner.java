import entity.Customer;
import utils.CustomerPoolGenerator;

import java.util.List;

public class Runner {
    public static void main(String[] args){
       List<Customer> customers = CustomerPoolGenerator.getCustomers();

       for (Customer customer : customers){
           new Thread(customer).start();

           try {
               Thread.sleep(2000);
           }catch (InterruptedException e){
               System.out.println(e.getMessage());
           }

       }
    }
}