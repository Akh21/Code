package com.example.springbootgraphqllearn.model;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Employee {


    @Id
    private UUID id;
    private String employeeNo;
    private String employeeName;


}
