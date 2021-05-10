package guru.springframework.springmvcrest.domain;

import guru.springframework.springmvcrest.repositories.CustomerRepository;
import guru.springframework.springmvcrest.services.CustomerService;
import guru.springframework.springmvcrest.services.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void FindById(){
        Customer c = new Customer();
        testEntityManager.persist(c);
        testEntityManager.flush();
        Customer found = customerRepository.getOne(1);
        Assert.assertEquals(found.getId(), c.getId());
    }

    @Test
    public void FindByEmail(){
        Customer c = new Customer();
        c.setEmail("johndoe@gmail.com");
        testEntityManager.persist(c);
        testEntityManager.flush();
        Customer found = customerRepository.findByEmail("johndoe@gmail.com");
        Assert.assertEquals(found.getEmail(), c.getEmail());
    }
}
