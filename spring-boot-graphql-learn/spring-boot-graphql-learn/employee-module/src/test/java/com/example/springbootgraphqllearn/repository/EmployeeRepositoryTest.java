package com.example.springbootgraphqllearn.repository;

import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;
import lombok.Data;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;


@DataR2dbcTest
class EmployeeRepositoryTest {

    @MockBean
    private EmployeeRepository employeeRepository;
    @Test
    @Disabled
    void getEmployeeByNo() {

    }
    @Test
    void addEmployee() {
        EmployeeRequest empPloyeeRequest = new EmployeeRequest();
        employeeRequest.setEmployeeNo("1001");
        employeeRequest.setEmployeeName("Chandan");

        Employee employee = new Employee(UUID.randomUUID(), "1001", "Chandan");
    }

    @Test
    @Disabled
    void deleteEmployeeByNo() {
    }
}