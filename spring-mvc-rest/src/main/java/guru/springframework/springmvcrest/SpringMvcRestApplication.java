package guru.springframework.springmvcrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class SpringMvcRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcRestApplication.class, args);
    }

}