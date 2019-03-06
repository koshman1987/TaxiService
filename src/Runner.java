import entity.Company;
import entity.Customer;
import utils.CustomerPoolGenerator;

import java.util.List;

public class Runner {
    public static void main(String[] args){
       Company company = Company.getCompany();
       List<Customer> customers = CustomerPoolGenerator.getCustomers();

       for (Customer customer : customers){
           customer.callTaxi(company.getCarPool());
       }
    }
}
