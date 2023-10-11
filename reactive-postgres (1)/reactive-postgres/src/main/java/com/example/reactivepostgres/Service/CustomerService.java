package com.example.reactivepostgres.Service;

import com.example.reactivepostgres.model.Customer;
import com.example.reactivepostgres.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Flux<Customer> getCustomers(){
        return repository.findAll();
    }
    public Mono<Customer> getProduct(String id){
        return repository.findById(Integer.valueOf(id));
    }
    public Mono<Customer> saveCustomer(Mono<Customer> customerMono){
        return customerMono;
    }
    public Mono<Customer> updateCustomer(Mono<Customer> customerMono){
        return repository.findById(id);
    }
}
