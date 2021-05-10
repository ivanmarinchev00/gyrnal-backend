package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Integer getJournalId(int customerId){
        if(findCustomerById(customerId).getJournal() != null){
            return findCustomerById(customerId).getJournal().getId();
        }
        else{
            return -1;
        }
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    Customer findByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    Boolean existsByEmail(String email){
        if(customerRepository.existsByEmail(email)==true){
            return true;
        }
        return false;
    }
}
