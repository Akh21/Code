package com.example.springbootgraphqllearn.service;

import com.example.springbootgraphqllearn.exception.EmployeeNotFoundException;
import com.example.springbootgraphqllearn.mapper.EntityToDto;
import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;
import com.example.springbootgraphqllearn.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.UUID;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(EmployeeServiceImpl.class)
//@WebFluxTest(EmployeeServiceImpl.class) only for controller Test
@Slf4j
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    Employee employee;
    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .id(UUID.randomUUID())
                .employeeName("Chandan")
                .employeeNo("1001").build();
    }
    @Test
    void findAllEmployees() {
        when(employeeRepository.findAll())
                .thenReturn(Flux.just(employee));

        StepVerifier.create(employeeService.findAllEmployees().log())
               // .expectNext(employee) we should not put this expectNext
                 .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }

    @Test
    void findAllEmployeeNegative(){
        when(employeeRepository.findAll())
                .thenReturn(Flux.error(new EmployeeNotFoundException("No Records Found")));

        StepVerifier.
                create(employeeService.findAllEmployees())
                .expectErrorMessage("No Records Found")
                .verify();
    }

    @Test
    void getEmployeeByNo() {
        when(employeeRepository.getEmployeeByNo(employee.getEmployeeNo()))
                .thenReturn(Mono.just(employee));

        StepVerifier.create(employeeService.getEmployeeByNo(employee.getEmployeeNo()))
                .expectNext(employee)
                .verifyComplete();
    }


    @Test
    void getEmployeeByNoNegative(){
        when(employeeRepository.getEmployeeByNo("1111"))
                .thenReturn(Mono.error(new EmployeeNotFoundException("Employee Not Found")));

        StepVerifier
                .create(employeeService.getEmployeeByNo("1111"))
                .expectErrorMessage("Employee Not Found")
                .verify();
    }

    @Test
    void addEmployee() {
        when(employeeRepository.addEmployee(employee.getEmployeeNo(), employee.getEmployeeName()))
                .thenReturn(Mono.just(employee));

        StepVerifier
                .create(employeeService.addEmployee(EntityToDto.mapEmployeeToEmployeeRequest(employee)))
                .expectNext(employee)
                .verifyComplete();
    }

    @Test
    void deleteEmployeeByNo() {

        when(employeeRepository.getEmployeeByNo(employee.getEmployeeNo()))
                .thenReturn(Mono.just(employee));
        // the above when is declared because the actual service method contains this getMethod in
        // deleteMethod


        when(employeeRepository.deleteEmployeeByNo(employee.getEmployeeNo()))
                .thenReturn(Mono.just(employee.getEmployeeNo()));

        StepVerifier
                .create(employeeService.deleteEmployeeByNo(employee.getEmployeeNo()))
                .expectNext(employee.getEmployeeNo())
                .verifyComplete();
    }


    @Test
    void deleteEmployeeByNoNegative(){
        when(employeeRepository.getEmployeeByNo("1111"))
                .thenReturn(Mono.error(new EmployeeNotFoundException("Employee Not Found")));

        when(employeeRepository.deleteEmployeeByNo("1111"))
                .thenReturn(Mono.error(new EmployeeNotFoundException("Employee Not Found")));

        StepVerifier
                .create(employeeService.deleteEmployeeByNo("1111"))
                .expectErrorMessage("Employee Not Found")
                .verify();
    }


    @Test
    void updateEmployee() {
        when(employeeRepository.getEmployeeByNo(employee.getEmployeeNo()))
                .thenReturn(Mono.just(employee));

        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                        .employeeName("varun")
                                .employeeNo(employee.getEmployeeNo())
                                        .build();

        employee.setEmployeeName("varun");

        when(employeeRepository.addEmployee(employee.getEmployeeNo(), employee.getEmployeeName()))
                .thenReturn(Mono.just(employee));

        StepVerifier.create(employeeService.updateEmployee(employee.getEmployeeNo(), employeeRequest))
                .expectNext(employee)
                .verifyComplete();

    }

    @Test
    void updateEmployeeNegative(){
        when(employeeRepository.getEmployeeByNo("1111"))
                .thenReturn(Mono.error(new EmployeeNotFoundException("Employee not Found")));

        EmployeeRequest employeeRequest = EmployeeRequest.builder()
                        .employeeNo("1111")
                                .employeeName(employee.getEmployeeName())
                                        .build();

        StepVerifier
                .create(employeeService.updateEmployee("1111", employeeRequest))
                .expectErrorMessage("Employee not Found")
                .verify();

    }


}