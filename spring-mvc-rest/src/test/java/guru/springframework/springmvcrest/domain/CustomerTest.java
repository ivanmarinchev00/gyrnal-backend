package guru.springframework.springmvcrest.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    public void getFirstNameTest(){
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "shakira", "ADMIN");
        assertEquals("Ivan", customer.getFirstName());
    }
    @Test
    public void getLastNameTest(){
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "shakira", "ADMIN");
        assertEquals("Marinchev", customer.getLastName());
    }
    @Test
    public void getIdTest(){
        Customer customer = new Customer("Ivan", "Marinchev", "ivanmarinchev00@gmail.com", "shakira", "ADMIN");
        assertEquals(0, customer.getId());
    }

}