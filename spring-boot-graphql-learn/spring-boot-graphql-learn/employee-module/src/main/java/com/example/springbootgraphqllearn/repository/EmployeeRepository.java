package com.example.springbootgraphqllearn.repository;

import com.example.springbootgraphqllearn.model.Employee;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface EmployeeRepository extends R2dbcRepository<Employee, UUID> {

    @Query("select * from employee where employee_no=:employeeNo;")
    Mono<Employee> getEmployeeByNo(String employeeNo);
    @Query("insert into employee(id, employee_no, employee_name)" +
            "values" +
            "( :employeeNo, :employeeName) on conflict(employee_no) " +
            "do update set employee_name=:employeeName Returning *;")
    Mono<Employee> addEmployee(String employeeNo, String employeeName);

    @Query("delete from employee where employee_no=:employeeNo returning employee_no;")
    Mono<String> deleteEmployeeByNo(String employeeNo);
}
