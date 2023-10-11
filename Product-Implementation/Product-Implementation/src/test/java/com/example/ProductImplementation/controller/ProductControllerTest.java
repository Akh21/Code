//package com.example.ProductImplementation.controller;
//
//import com.example.ProductImplementation.exception.ProductNotFoundException;
//import com.example.ProductImplementation.model.Product;
//import com.example.ProductImplementation.repository.ProductRepository;
//import com.example.ProductImplementation.service.ProductService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier;
//
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(SpringExtension.class)
//@WebFluxTest(Controller.class)
//@Import({ProductService.class, ProductRepository.class})
//public class ProductControllerTest {
//
//    private static final UUID uuid1 = UUID.randomUUID();
//    private static final UUID uuid2 = UUID.randomUUID();
//    @Autowired
//    WebTestClient webTestClient;
//
//    @MockBean
//    ProductRepository repository;
//
//    @MockBean
//    ProductService service;
//
//
//    @Test
//    public void addProduct(){
//
//        Product product= new Product(UUID.randomUUID()
//                ,"1309","macbook");
//
//
//        when(service.addProduct(product))
//                .thenReturn(Mono.empty());
//
//        WebTestClient.ResponseSpec response=webTestClient.post().uri("/api/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .body(Mono.just(product), Product.class)
//                .exchange();
//
//        response.expectStatus().isOk()
//                .expectBody()
//                .consumeWith(System.out::println);
//    }
//
//    @Test
//    void getAllProductsTestPositive(){
//
//        Flux<Product> productFlux= Flux.just(new Product(uuid1,"123","macbook"),
//                new Product(uuid2,"234","iphone"));
//
//        when(service.findAllProducts()).thenReturn(Flux.just(new Product(uuid1,"123","macbook"),
//                new Product(uuid2,"234","iphone")));
//
//        Flux<Product> responseBody = webTestClient.get().uri("/api/product")
//                .exchange()
//                .expectStatus().isOk()
//                .returnResult(Product.class)
//                .getResponseBody();
//
//        StepVerifier.create(responseBody)
//                .expectSubscription()
//                .expectNext(new  Product(uuid1,"123","macbook"))
//                .expectNext(new Product(uuid2,"234","iphone"))
//                .verifyComplete();
//
//    }
//
//    @Test
//    void getAllProductNegativeTest() {
//        when(service.findAllProducts())
//                .thenReturn(Flux.error(new ProductNotFoundException("No Product Found")));
//
//        webTestClient
//                .get()
//                .uri("/api/product")
//                .exchange()
//                .expectStatus().isNotFound()
//                .expectBody()
//                .jsonPath("$.message").isEqualTo("No Product Found");
//        }
//
//    @Test
//void getProductByNoTest(){
//
//    when(service.getByNo("123"))
//            .thenReturn(Mono.just(new Product(uuid1,"123","laptop")));
//
//    webTestClient
//            .get()
//            .uri("/api/product/123")
//            .exchange()
//            .expectStatus().is2xxSuccessful()
//            .expectBody()
//            .jsonPath("$.productName").isEqualTo("laptop");
//}
//
//    @Test
//   void getProductByNoNegativeTest(){
//
//       var productNo="1309";
//
//       when(service.getByNo(productNo))
//               .thenReturn(Mono.error(new ProductNotFoundException("Product with "+productNo+" not found")));
//
//       webTestClient
//               .get()
//               .uri("/api/product/1309")
//               .exchange()
//               .expectStatus().isNotFound()
//               .expectBody()
//               .jsonPath("$.message").isEqualTo("Product with "+productNo+"  not found");
//
//   }
//
//    @Test
//    void deleteProductByNoTest(){
//        when(service.deleteByNo("123"))
//                .thenReturn(Mono.empty());
//
//        webTestClient
//                .delete()
//                .uri("/api/product/123")
//                .exchange()
//                .expectStatus().isOk();
//    }
//
//
//
//
//}

