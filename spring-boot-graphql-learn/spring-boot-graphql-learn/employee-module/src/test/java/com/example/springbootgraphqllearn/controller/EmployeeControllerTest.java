package com.example.springbootgraphqllearn.controller;

import com.example.springbootgraphqllearn.service.EmployeeServiceImpl;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest(EmployeeServiceImpl.class)
class EmployeeControllerTest {

    public MonoGraphQLClient monoGraphQLClient;
    private WebClient webClient;

    @Test
    void getAll() {

    }


    @Test
    void getEmployeeByNo() {
    }

    @Test
    void addEmployee() {
    }

    @Test
    void deleteEmployeeByNo() {
    }

    @Test
    void updateEmployee() {
    }
}