package com.andynator.customerservice.mappers;

import com.andynator.customerservice.dtos.CustomerDTO;
import com.andynator.customerservice.entities.Customer;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {
    CustomerMapper underTest=new CustomerMapper();

    @Test
    public void shouldMapCustomerToCustomerDTO() {
        Customer givenCustomer = Customer.builder()
                .id(1L).firstName("Andy").lastName("Jeanty").email("and@gmail.com").build();
        CustomerDTO expected = CustomerDTO.builder()
                .id(1L).firstName("Andy").lastName("Jeanty").email("and@gmail.com").build();

        CustomerDTO result = underTest.fromCustomer(givenCustomer);

        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }
    @Test
    public void shouldMapCustomerDTOToCustomer() {
        CustomerDTO givenCustomerDTO = CustomerDTO.builder()
                .id(1L).firstName("Andy").lastName("Jeanty").email("and@gmail.com").build();
        Customer expected = Customer.builder()
                .id(1L).firstName("Andy").lastName("Jeanty").email("and@gmail.com").build();
        Customer result = underTest.fromCustomerDTO(givenCustomerDTO);

        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);

    }
    @Test
    public void shouldMapListOfCustomersToListOfCustomers() {
        List<Customer> givenCustomers = List.of(
                Customer.builder()
                        .id(1L).firstName("Andy").lastName("Jeanty").email("and@gmail.com").build(),
                Customer.builder()
                        .id(2L).firstName("lineda").lastName("Jean").email("line@gmail.com").build(),
                Customer.builder()
                        .id(3L).firstName("waseletta").lastName("Jeanty-Jean").email("wase@gmail.com").build()
        );

        List<CustomerDTO> expected = List.of(
                CustomerDTO.builder()
                        .id(1L).firstName("Andy").lastName("Jeanty").email("and@gmail.com").build(),
                CustomerDTO.builder()
                        .id(2L).firstName("lineda").lastName("Jean").email("line@gmail.com").build(),
                CustomerDTO.builder()
                        .id(3L).firstName("waseletta").lastName("Jeanty-Jean").email("wase@gmail.com").build()
        );
        List<CustomerDTO> result = underTest.fromListCustomers(givenCustomers);
        assertThat(result).isNotNull();
        assertThat(expected).usingRecursiveComparison().isEqualTo(result);
    }

    @Test
    public void shouldNotMapNullCustomerToCustomerDTO() {
        Customer givenCustomer = null;
        AssertionsForClassTypes.assertThatThrownBy(()->underTest.fromCustomer(givenCustomer))
                .isInstanceOf(IllegalArgumentException.class);
    }

}