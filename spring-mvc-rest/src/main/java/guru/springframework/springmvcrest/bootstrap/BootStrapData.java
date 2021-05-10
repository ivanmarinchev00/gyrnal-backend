package guru.springframework.springmvcrest.bootstrap;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private final CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder;
    public BootStrapData(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) throws Exception {
    System.out.println("Loading customer data");
    Customer c1 = new Customer();
    c1.setFirstname("Ivan");
    c1.setLastname("Marinchev");
    c1.setPassword(passwordEncoder.encode("shakira"));
    c1.setRoles("ADMIN");
    c1.setEmail("ivanmarinchev00@gmail.com");
    customerRepository.save(c1);

    Customer c2 = new Customer();
    c2.setFirstname("Jordan");
    c2.setLastname("Radushev");
    c2.setPassword(passwordEncoder.encode("radushko"));
    c2.setRoles("USER");
    c2.setEmail("jjradushev@gmail.com");
    customerRepository.save(c2);

    Customer c3 = new Customer();
    c3.setFirstname("Biser");
    c3.setLastname("Usufi");
    c3.setEmail("busufi@gmail.com");
    c3.setPassword(passwordEncoder.encode("biskata"));
    c3.setRoles("USER");
    customerRepository.save(c3);
    System.out.println("Customers saved: " + customerRepository.count());
    }
}
