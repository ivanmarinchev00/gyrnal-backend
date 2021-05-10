package guru.springframework.springmvcrest;

import guru.springframework.springmvcrest.controllers.CustomerController;
import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.repositories.CustomerRepository;
import guru.springframework.springmvcrest.services.CustomerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.ignoreStubs;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CustomerController.class)
@SpringBootTest
class SpringMvcRestApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    private List<Customer> customers;

    @MockBean
    private CustomerRepository customerRepository;


    //void shouldFetchAllCustomers() throws Exception{
       // given(customerService.findAllCustomers()).willReturn(customers);
        //this.mockMvc.perform(get("/api/v1/customers")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(customers.size())));
    //}
}



