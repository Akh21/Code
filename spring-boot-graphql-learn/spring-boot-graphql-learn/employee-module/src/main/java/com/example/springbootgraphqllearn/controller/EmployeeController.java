package com.example.springbootgraphqllearn.controller;


import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;
import com.example.springbootgraphqllearn.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@Slf4j
public class EmployeeController{

        private final EmployeeService employeeService;

        @Autowired
        public EmployeeController(EmployeeService employeeService) {
                this.employeeService = employeeService;
        }

        @QueryMapping
        Flux<Employee> getAll(){
                return employeeService.findAllEmployees();
        }

        @QueryMapping
        Mono<Employee> getEmployeeByNo(@Argument String employeeNo){
                return employeeService.getEmployeeByNo(employeeNo).log();
        }

        @MutationMapping
        Mono<Employee> addEmployee(@Argument(name = "input") EmployeeRequest employeeRequest){
                log.info(employeeRequest.toString());
                return employeeService.addEmployee(employeeRequest);
        }

        @MutationMapping
        Mono<String> deleteEmployeeByNo(@Argument String employeeNo){
                return employeeService.deleteEmployeeByNo(employeeNo);
        }

        @MutationMapping
        Mono<Employee> updateEmployee(@Argument  String employeeNo, @Argument EmployeeRequest employeeRequest){
                return employeeService.updateEmployee(employeeNo, employeeRequest);
        }

}
