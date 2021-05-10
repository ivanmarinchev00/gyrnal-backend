package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Customer;

import java.util.List;

public interface CustomerService {
    Customer findCustomerById(int id);
    List<Customer> findAllCustomers();
    Integer getJournalId(int id);
    Customer saveCustomer(Customer customer);
}
