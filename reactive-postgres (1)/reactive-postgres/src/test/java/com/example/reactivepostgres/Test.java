package com.example.reactivepostgres;
import com.example.reactivepostgres.Controller.CustomerController;
import com.example.reactivepostgres.model.Customer;
import com.example.reactivepostgres.repository.CustomerRepository;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
@SpringBootTest
class Test {
	@Autowired
	WebTestClient webTestClient;

	@MockBean
	CustomerRepository customerRepository;

	@org.junit.jupiter.api.Test
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