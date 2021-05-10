package guru.springframework.springmvcrest.controllers;

import guru.springframework.springmvcrest.domain.AuthenticationRequest;
import guru.springframework.springmvcrest.domain.AuthenticationResponse;
import guru.springframework.springmvcrest.domain.Customer;
import guru.springframework.springmvcrest.domain.MyUserPrincipal;
import guru.springframework.springmvcrest.repositories.CustomerRepository;
import guru.springframework.springmvcrest.services.CustomerService;
import guru.springframework.springmvcrest.services.MyUserDetailsService;
import guru.springframework.springmvcrest.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {
    public static final String BASE_URL = "/api/v1/auth";
    private CustomerService customerService;
    @Autowired
    private final MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil JwtUtilToken;
    @Autowired
    CustomerRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public AuthController(CustomerService customerService, MyUserDetailsService myUserDetailsService) {
        this.customerService = customerService;
        this.myUserDetailsService = myUserDetailsService;
    }

    @RequestMapping(value ="/signin", method = RequestMethod.POST)
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            String username = authenticationRequest.getEmail();
            String password = authenticationRequest.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (BadCredentialsException e){
            throw new Exception("Wrong username or password");
        }
        final MyUserPrincipal userDetails = (MyUserPrincipal) myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String Jwt = JwtUtilToken.generateToken(userDetails);
        Integer journalId = customerService.getJournalId(userDetails.getId());
        return ResponseEntity.ok(new AuthenticationResponse(Jwt, userDetails.getRole(), journalId, userDetails.getId()));
    }

    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        if(!repository.existsByEmail(authenticationRequest.getEmail())){
            Customer newCustomer = new Customer(authenticationRequest.getFirstName(), authenticationRequest.getLastName() ,authenticationRequest.getEmail(), encoder.encode(authenticationRequest.getPassword()), "USER");
            customerService.saveCustomer(newCustomer);
            final MyUserPrincipal userDetails = new MyUserPrincipal(newCustomer);
            final String Jwt = JwtUtilToken.generateToken(userDetails);
            return ResponseEntity.ok(new AuthenticationResponse(Jwt, userDetails.getRole(), -1, userDetails.getId()));
        }
        else
        {
            throw new Exception("Email is already in use");
        }

    }
}
