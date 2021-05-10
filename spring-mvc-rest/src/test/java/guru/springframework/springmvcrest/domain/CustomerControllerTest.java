package guru.springframework.springmvcrest.domain;



import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springmvcrest.services.CustomerServiceImpl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
@AutoConfigureTestDatabase
public class CustomerControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockmvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @MockBean
    private CustomerServiceImpl customerService;
    private List<Customer> customers;

    ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    public void SetMockMvc(){
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        customers = new ArrayList<Customer>();
        customers.add(customer);
    }



    @Test
    public void GetAllCustomers() throws Exception{
        Mockito.when(customerService.findAllCustomers()).thenReturn(customers);
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customers/getall");
        MvcResult result = mockmvc.perform(builder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(customers);

        Assert.assertEquals(expected, content);
    }

    @Test
    public void AddCustomerTest() throws Exception{
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        Mockito.when(customerService.saveCustomer(customer)).thenReturn(customer);

        String customerJson = "{\"firstname\":\"Ivan\",\"lastName\":\"Marinchev\",\"email\":\"ivanmarinchev00@gmail.com\",\"password\":\"symere\",\"roles\":\"ADMIN\"}";

        mockmvc.perform(post("/api/v1/customers").contentType(APPLICATION_JSON_UTF8)
                .content(customerJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void GetCustomerById() throws Exception{
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "symere", "ADMIN");
        Mockito.when(customerService.findCustomerById(0)).thenReturn(customer);
        RequestBuilder builder = MockMvcRequestBuilders.get("/api/v1/customers/0");
        MvcResult result = mockmvc.perform(builder).andReturn();
        String content = result.getResponse().getContentAsString();

        String expected = objectMapper.writeValueAsString(customer);

        Assert.assertEquals(expected, content);
    }

}
