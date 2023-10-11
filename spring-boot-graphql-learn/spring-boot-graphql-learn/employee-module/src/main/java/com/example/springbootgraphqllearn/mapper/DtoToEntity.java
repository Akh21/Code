package com.example.springbootgraphqllearn.mapper;

import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;

public final class DtoToEntity {

    private DtoToEntity(){

    }

    public static Employee mapEmployeeRequestToEmployee(EmployeeRequest employeeRequest){
        return Employee.builder()
                .employeeNo(employeeRequest.getEmployeeNo())
                .employeeName(employeeRequest.getEmployeeName())
                .build();
    }

}
