package guru.springframework.springmvcrest.controllers;
import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    @Autowired
    private PasswordEncoder encoder;
    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getall") // Change
    @PreAuthorize("hasRole('ADMIN')")
    List<Customer> getAllCustomers(){
       return customerService.findAllCustomers();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.findCustomerById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN' || 'USER')")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
    }


}
