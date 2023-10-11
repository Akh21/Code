package com.example.springbootgraphqllearn.service;

import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;
import com.example.springbootgraphqllearn.model.EmployeeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeService {
    Flux<Employee> findAllEmployees();
    Mono<Employee> getEmployeeByNo(String employeeNo);
    Mono<Employee> addEmployee(EmployeeRequest employeeRequest);
    Mono<String> deleteEmployeeByNo(String employeeNo);
    Mono<Employee> updateEmployee(String employeeNo, EmployeeRequest employeeRequest);


    // for employeeResponse

    Mono<EmployeeResponse> updateEmployeeResponse(String employeeNo, EmployeeRequest employeeRequest);
}
