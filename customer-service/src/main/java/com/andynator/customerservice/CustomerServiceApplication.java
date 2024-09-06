package com.andynator.customerservice;

import com.andynator.customerservice.entities.Customer;
import com.andynator.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	@Profile("!test")
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			customerRepository.save(Customer.builder()
					.firstName("Andy")
					.lastName("Jeanty").email("and@gmail.com").build());
			customerRepository.save(Customer.builder()
					.firstName("Lineda")
					.lastName("Jean").email("line@gmail.com").build());
			customerRepository.save(Customer.builder()
					.firstName("Waseletta")
					.lastName("Jeanty-Jean").email("wase@gmail.com").build());
		};
	}

}
