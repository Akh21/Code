package com.example.ProductImplementation.service;

import com.example.ProductImplementation.exception.ProductNotFoundException;
import com.example.ProductImplementation.model.Product;
import com.example.ProductImplementation.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private  ProductRepository productRepository;

    private final ObjectMapper objectMapper= new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public Flux<Product> findAllProducts(){
        return  productRepository.findAll();


    }

    public Mono<Product> getByNo(String productNo){
        return productRepository.findByNo(productNo);

    }

    public Mono<Product> addProduct(Product product){
        return  productRepository.addProduct(product.getProductNo(), product.getProductName());
    }

    public Mono<Product> updateProductByNo(String productNo, Product product){
        return productRepository.findByNo(productNo)
                .flatMap(abc -> {
                    Map<String, Object> fieldMap= objectMapper.convertValue(product,
                            new TypeReference<Map<String, Object>>() {});
                    Product product1= abc;
                    fieldMap.forEach((k, v)->{
                        Field field = ReflectionUtils.findField(Product.class, k);
                        field.setAccessible(true);
                        ReflectionUtils.setField(field, product1, v);
                    });

                    abc.setProductName(product1.getProductName());

                    return productRepository.addProduct(abc.getProductNo(), abc.getProductName());


                });

    }
    public Mono<Product> deleteByNo(String productNo){
        return productRepository.deleteByNo(productNo);
    }
}
