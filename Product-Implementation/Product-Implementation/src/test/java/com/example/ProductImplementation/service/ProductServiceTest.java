package com.example.ProductImplementation.service;

import com.example.ProductImplementation.model.Product;
import com.example.ProductImplementation.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import javax.swing.plaf.PanelUI;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
public class ProductServiceTest {

    private static final UUID uuid1 = UUID.randomUUID();
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    Product product;
    @BeforeEach
    void setup(){
        product=Product.builder()
                .id(uuid1)
                .productNo("100")
                .productName("shoes").build();

    }
//    @Test
//    void  findAllProducts(){
//        when(productRepository.findAll())
//                .thenReturn(Flux.just(product));
//
//        StepVerifier.create(productService.findAllProducts().log())
//                .consumeNextWith(Assertions::assertNotNull)
//                .verifyComplete();
//
//    }
//
//    @Test
//    public void getProductByNoTest(){
//        when(productRepository.findByNo(product.getProductNo()))
//                .thenReturn(Mono.just(product));
//
//        Mono<Product> getProduct= productService.getByNo((product.getProductNo()));
//
//        StepVerifier
//                .create(getProduct)
//                .expectNext(new Product(uuid1,"100","shoes"))
//                .verifyComplete();
//    }
//
//    @Test
//    public void deleteProductTest(){
//        when(productRepository.findByNo(product.getProductNo()))
//                .thenReturn((Mono.just(product)));
//         when(productRepository.deleteByNo(product.getProductNo()))
//                 .thenReturn(Mono.empty());
//
//         Mono<Product> deleteProduct=(productService.deleteByNo(product.getProductNo()));
//
//         StepVerifier
//                 .create(deleteProduct)
//                 .expectComplete();
//    }
//
//    @Test
//    private void productPatchTest(){
//        Product product1=Product.builder()
//                .id(uuid1)
//                .productNo("100")
//                .productName("shoes")
//                .build();
//        when(productRepository.findByNo(product.getProductNo()))
//                .thenReturn(Mono.just(product1));
//
//        Product product2= Product.builder()
//                .productName("laptop")
//                .productNo("234")
//                .build();
//        product.setProductName("shoes");
//
//        when(productRepository.addProduct(product.getProductNo(),product.getProductName()))
//                .thenReturn(Mono.just(product2));
//
//        System.out.println(product);
////        System.out.println(product);
////        System.out.println(product);
//
//        Mono<Product> updateProduct=productService.updateProductByNo(product.getProductNo(),product1);
//
//        StepVerifier
//                .create(updateProduct)
//                .expectNext(product1)
//                .expectComplete();
//
//
//    }
}
