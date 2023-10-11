package com.example.springbootgraphqllearn.mapper;

import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;

public final class EntityToDto {
    private EntityToDto(){

    }

    public static EmployeeRequest mapEmployeeToEmployeeRequest(Employee employee){
        return EmployeeRequest.builder()
                .employeeNo(employee.getEmployeeNo())
                .employeeName(employee.getEmployeeName())
                .build();
    }
}
