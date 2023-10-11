package com.example.attributes.attributes.scalarConfig;


import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.util.List;

import static graphql.scalars.ExtendedScalars.*;

@Configuration
@RequiredArgsConstructor

public class Scalars {

    @Bean
    public RuntimeWiringConfigurer configurer() {

        List<GraphQLScalarType> qlScalarTypes = List.of(
                Object,
                Json
        );
        return c -> qlScalarTypes.forEach(c::scalar);
    }

}
