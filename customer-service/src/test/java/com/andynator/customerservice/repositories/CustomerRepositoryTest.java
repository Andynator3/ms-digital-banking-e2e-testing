package com.andynator.customerservice.repositories;

import com.andynator.customerservice.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;


@ActiveProfiles("test")
@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    @BeforeEach
    void setUp() {
        System.out.println("********************************");
        customerRepository.save(Customer.builder()
                .firstName("Andy")
                .lastName("Jeanty").email("and@gmail.com").build());
        customerRepository.save(Customer.builder()
                .firstName("Lineda")
                .lastName("Jean").email("line@gmail.com").build());
        customerRepository.save(Customer.builder()
                .firstName("Waseletta")
                .lastName("Jeanty-Jean").email("wase@gmail.com").build());
        System.out.println("********************************");
    }
    @Test
    void shouldFindCustomerByEmail() {
        String givenEmail="and@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isPresent();
    }
    @Test
    void shouldNotFindCustomerByEmail() {
        String givenEmail="ddd@ddd@gmail.com";
        Optional<Customer> result = customerRepository.findByEmail(givenEmail);
        assertThat(result).isEmpty();
    }
    @Test
    void shouldFindCustomersByFirstName() {
        String keyword="e";
        List<Customer> expected = List.of(
                Customer.builder().firstName("Lineda").lastName("Jean").email("line@gmail.com").build(),
                Customer.builder().firstName("Waseletta").lastName("Jeanty-Jean").email("wase@gmail.com").build()
        );
        List<Customer> result = customerRepository.findByFirstNameContainsIgnoreCase(keyword);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        //assertThat(result.size()).isEqualTo(expected.size());
        assertThat(result).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }
}