package com.example.reactivepostgres.repository;


import com.example.reactivepostgres.model.Customer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {

}
