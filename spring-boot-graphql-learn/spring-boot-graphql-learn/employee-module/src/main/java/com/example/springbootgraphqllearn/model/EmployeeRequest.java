package com.example.springbootgraphqllearn.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeRequest {

    private String employeeNo;
    private String employeeName;

//    public Employee getEmployeeEnity(){
//        return Employee.builder()
//                .employeeNo(this.getEmployeeNo())
//                .employeeName(this.getEmployeeName())
//                .build();
//    }
}
