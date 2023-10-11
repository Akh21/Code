package com.example.ProductImplementation.controller;

import com.example.ProductImplementation.model.Product;
import com.example.ProductImplementation.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

//@RestController
//@RequestMapping("/api/product")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;


// @GetMapping()
//    @SchemaMapping
    @QueryMapping("getAllProductsGraphql")
    public Flux<Product> getAllProducts(){return productService.findAllProducts();
    }


//    @GetMapping("/{productNo}")
    @QueryMapping("getByNo")
    public Mono<Product> getByNo(@Argument String productNo){
        return productService.getByNo(productNo);
    }


//    @PostMapping()
    @MutationMapping("createProduct")
    public Mono<Product> addProduct(@Argument ProductInput product){
        Product product1=new Product();
        product1.setProductNo(product.getProductNo());
        product1.setProductName(product.getProductName());
       return productService.addProduct(product1);
    }


//    @PatchMapping("/patch/{productNo}")
    @MutationMapping("updateProduct")
    public Mono<Product> updateProductByNo(@Argument String productNo,
                                           @Argument Product product){
     return productService.updateProductByNo(productNo, product);
    }


//    @DeleteMapping("/{productNo}")
    @MutationMapping("deleteProductByNo")
    public Mono<Product> deleteByNo(@Argument String productNo){
     return productService.deleteByNo(productNo);
    }
}

@Getter
@Setter
class ProductInput{

    private String productNo;
    private String productName;
}
