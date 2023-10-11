package com.example.springbootgraphqllearn;

import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.repository.EmployeeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringBootGraphqlLearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGraphqlLearnApplication.class, args);
	}

//	@Bean
//	ApplicationRunner applicationRunner(EmployeeRepository employeeRepository){
//		return args -> {
//			employeeRepository.saveAll(List.of(
//					new Employee(null, "1001", "Chandan"),
//					new Employee(null, "1002", "Varun")
//			));
//		};
//	}

}
