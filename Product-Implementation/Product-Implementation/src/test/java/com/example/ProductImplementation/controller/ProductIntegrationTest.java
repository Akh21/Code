package com.example.ProductImplementation.controller;

import com.example.ProductImplementation.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.List;

@SpringBootTest
@AutoConfigureGraphQlTester
public class ProductIntegrationTest {
    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void addProductTest() {
        String query = "mutation {createProduct(product: {productNo:\"123\" productName: \"laptop\"}){id}}";
        Product product =graphQlTester.document(query)
                .execute()
                .path("data.createProduct")
                .entity(Product.class)
                .get();
    }

    @Test
    void findAllProductTest() {
        String query = "{ getAllProductsGraphql{id productNo productName}}";
        List<Product> products = graphQlTester.document(query)

                .execute()
                .path("data.getAllProductsGraphql[*]")
                .entityList(Product.class)
                .get();
    }
}
