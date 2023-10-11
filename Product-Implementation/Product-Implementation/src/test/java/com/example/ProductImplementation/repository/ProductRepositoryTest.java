package com.example.ProductImplementation.repository;

import com.example.ProductImplementation.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;


@DataR2dbcTest
public class ProductRepositoryTest {

    @MockBean
    private ProductRepository repository;
    @Test
    void addProduct(){
        Product product=new Product();
        product.setProductNo("100");
        product.setProductName("laptop");

        Product product1=new Product(UUID.randomUUID(),"100","laptop");
    }
}
