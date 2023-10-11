package com.Sample.Context.contextstructure.ScalarImpl;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static graphql.scalars.ExtendedScalars.Json;
import static graphql.scalars.ExtendedScalars.UUID;

@Configuration
@RequiredArgsConstructor
public class Scalar {

    @Bean
    public RuntimeWiringConfigurer configurer() {

        List<GraphQLScalarType> qlScalarTypes = List.of(

               UUID,
                Json



        );
        return c -> qlScalarTypes.forEach(c::scalar);
    }
}
