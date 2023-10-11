package com.example.ProductImplementation.controller;

import com.example.ProductImplementation.model.Product;
import com.example.ProductImplementation.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
@AutoConfigureGraphQlTester
//@Import(ProductService.class)
public class ProductControllerGraphqlTest {

    @Autowired
    private GraphQlTester tester;

    @MockBean
    ProductService productService;

@Test
    void addProductTest() {
    Product     product = new Product();
    product.setProductNo("123");
    product.setProductName("laptop");
    when(productService.addProduct(product))
            .thenReturn(Mono.just(new Product(null, "123", "laptop")));
        String query="mutation {createProduct(product: {productNo:\"123\" productName: \"laptop\"}){productNo}}";
        Product product1= (Product) tester.document(query)
                .execute()
                .path("data.createProduct")
                .entity(Product.class)
                .get();
    }

@Test
    void findAllProductTest(){
    String query="{ getAllProductsGraphql{id productNo productName}}";
        List<Product> products = tester.document(query)

                .execute()
                .path("data.getAllProductsGraphql[*]")
                .entityList(Product.class)
                .get();

//        Assertions.assertTrue(products.size()>0);
//        Assertions.assertNotNull(products.get(0).getId());
//        Assertions.assertNotNull(products.get(0).getProductName());
    }
    @Test
    void findAllProductNegativeTest(){
        when(productService.findAllProducts()).thenReturn(Flux.empty());

        //language=GraphQL
        String document = """
               query{
                       getAllProductsGraphql {
                            productNo
                            productName

                           }
                       }

               """;

        tester.document(document)
                .execute()
                .path("getAllProductsGraphql")
                .entityList(Product.class)
                .hasSize(0);
}
@Test
    void findByProductNoTest() {


         when(productService.getByNo("123"))
                 .thenReturn(Mono.just(new Product(null,"123","laptop")));
   String document= """
            query getByNo($productNo: String){
            getByNo(productNo: $productNo){
                 productNo
                 productName

}
}
""" ;
   tester.document(document)
           .variable("productNo","123")
           .execute()
           .path("getByNo")
           .entity(Product.class)
           .satisfies(product -> {
               assertEquals("laptop",product.getProductName());
               assertEquals("123",product.getProductNo());
           });
}

@Test
void  findByProductNoNegativeTest(){
    when(productService.getByNo("123"))
            .thenReturn(Mono.empty());

    //language=GraphQL
    String document= """
                query studentByUsn($studentUsn: String){
                getByNo(productNo: $studentUsn){

                    productNo
                    productName
                }
                }

                """;
    tester.document(document)
            .variable("productNo", "123")
            .execute()
            .path("getByNo")
            .valueIsNull();
}

@Test
void deleteProductByNoTest(){
    String Num="123";
    when(productService.deleteByNo("123"))
            .thenReturn(Mono.empty());

    //language=GraphQL
    String query = """
                mutation
                    deleteByUsn($productNo: String){
                        deleteProductByNo(productNo:$productNo)
                        }
                        """;

    tester.document(query)
            .variable("$studentUsn",Num )
            .execute()
            .path("deleteProductByNo");
}
}
