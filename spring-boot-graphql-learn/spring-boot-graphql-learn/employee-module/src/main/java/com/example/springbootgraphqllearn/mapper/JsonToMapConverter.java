package com.example.springbootgraphqllearn.mapper;


import com.example.springbootgraphqllearn.model.Employee;
import com.example.springbootgraphqllearn.model.EmployeeRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import io.r2dbc.postgresql.codec.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

@AllArgsConstructor
@ReadingConverter
@Slf4j
public class JsonToMapConverter implements Converter<Json, Map<String, Object>>{
    private final ObjectMapper objectMapper;


    @Override
    public Map<String, Object> convert(Json source) {
        try {
            return objectMapper.readValue(source.toString(), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            log.error("Problem while parsing JSON: {}",source, e);
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> convertValue(EmployeeRequest employeeRequest) {
        try {
            return objectMapper.readValue(employeeRequest.toString(), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            log.error("Problem while parsing JSON: {}", employeeRequest, e);
            throw new RuntimeException(e);
        }
    }
}
