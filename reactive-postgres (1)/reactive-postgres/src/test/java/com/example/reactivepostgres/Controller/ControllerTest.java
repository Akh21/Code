package com.example.reactivepostgres.Controller;

import com.example.reactivepostgres.model.Customer;
import com.example.reactivepostgres.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CustomerController.class)
class ControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @MockBean
    CustomerRepository customerRepository;

@Test
    void getCustomerTest(){

        Flux<Customer> customerFlux=Flux.just(new Customer(1,"the akash"),new Customer(2,"shikhar"));
        Mockito.when(customerRepository.findAll()).thenReturn(customerFlux);

        webTestClient.get()
                .uri("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].name").isEqualTo("the akash");


    }
}
