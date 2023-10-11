package com.example.springbootgraphqllearn.service;


import com.example.springbootgraphqllearn.exception.EmployeeNotFoundException;
import com.example.springbootgraphqllearn.mapper.DtoToEntity;
import com.example.springbootgraphqllearn.mapper.EntityToDto;
import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;
import com.example.springbootgraphqllearn.model.EmployeeResponse;
import com.example.springbootgraphqllearn.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Map;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

//    @Autowired
//    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ObjectMapper objectMapper){
//        this.employeeRepository=employeeRepository;
//        this.objectMapper = objectMapper;
//    }
    @Override
    public Flux<Employee> findAllEmployees() {

        return employeeRepository.findAll()
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException("No Employees to show")));
    }
    @Override
    public Mono<Employee> getEmployeeByNo(String employeeNo) {

        return employeeRepository.getEmployeeByNo(employeeNo)
                .switchIfEmpty(Mono.error(new EmployeeNotFoundException("No Employee Found with No "+employeeNo)));
    }

    @Override
    public Mono<Employee> addEmployee(EmployeeRequest employeeRequest) {
        return employeeRepository.addEmployee(employeeRequest.getEmployeeNo(), employeeRequest.getEmployeeName());
    }

    @Override
    public Mono<String> deleteEmployeeByNo(String employeeNo) {
        return employeeRepository.getEmployeeByNo(employeeNo)
                        .switchIfEmpty(Mono.error(new EmployeeNotFoundException("No Employee Found for "+employeeNo)))
                        .flatMap(employee->employeeRepository.deleteEmployeeByNo(employee.getEmployeeNo()));
    }

    @Override
    public Mono<Employee> updateEmployee(String employeeNo, EmployeeRequest employeeRequest) {
      return employeeRepository.getEmployeeByNo(employeeNo)
              .switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee Not Found for "+employeeNo)))
              .flatMap(employee -> {
//                  employeeRepository.getEmployeeByNo(employee.getEmployeeNo())
//                          .switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee Not Found for "+employee.getEmployeeNo())));

                  Map<String, Object> fieldMap = objectMapper.convertValue(employeeRequest,
                          new TypeReference<Map<String, Object>>() {});

                  EmployeeRequest employeeRequest1 = EntityToDto.mapEmployeeToEmployeeRequest(employee);
                  fieldMap.forEach((k, v)->{
                      Field field = ReflectionUtils.findField(EmployeeRequest.class, k);
                      field.setAccessible(true);
                      ReflectionUtils.setField(field, employeeRequest1, v);
                  });

                  //Employee employee1 = DtoToEntity.mapEmployeeRequestToEmployee(employeeRequest1);

                  employee.setEmployeeName(employeeRequest1.getEmployeeName());

                  return employeeRepository.addEmployee(employee.getEmployeeNo(), employee.getEmployeeName())
                          .switchIfEmpty(Mono.error(new EmployeeNotFoundException("Employee Not Found")));
              });
    }

    @Override
    public Mono<EmployeeResponse> updateEmployeeResponse(String employeeNo, EmployeeRequest employeeRequest) {
        return null;
    }

}
