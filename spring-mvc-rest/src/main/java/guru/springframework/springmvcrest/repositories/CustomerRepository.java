package guru.springframework.springmvcrest.repositories;
import guru.springframework.springmvcrest.domain.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
    Boolean existsByEmail(String email);
}
