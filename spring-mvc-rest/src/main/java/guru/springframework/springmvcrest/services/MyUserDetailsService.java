package guru.springframework.springmvcrest.services;

import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.domain.MyUserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    private final CustomerServiceImpl customerService;

    public MyUserDetailsService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = customerService.findByEmail(email);
        if(customerService.existsByEmail(email) == null)
        {
            throw new UsernameNotFoundException(email);
        }
        return new MyUserPrincipal(customer);
    }
}
