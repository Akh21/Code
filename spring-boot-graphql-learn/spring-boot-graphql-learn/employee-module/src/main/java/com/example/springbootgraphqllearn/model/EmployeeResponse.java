package com.example.springbootgraphqllearn.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeResponse {

    private String employeeNo;
    private String employeeName;
}
