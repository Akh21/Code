package com.example.ProductImplementation.repository;

import com.example.ProductImplementation.model.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, UUID> {

@Query("INSERT INTO product(id, product_no, product_name)" +
" VALUES " +
"( uuid_generate_v1(), :productNo, :productName)"+
        "on conflict(product_no) do " +
        "update set  product_name=:productName Returning *;")
    Mono<Product> addProduct(String productNo, String productName);

@Query("SELECT * FROM product; ")
    Flux<Product> findAll();

@Query("SELECT * FROM product where product_no= :productNo;")
    Mono<Product> findByNo(String productNo);

//@Query("SELECT * FROM product WHERE product_no= :productName;")
//Mono<Product> getProductByNo(String productNo);


@Query("DELETE FROM product WHERE product_no= :productNo returning product_no;")
    Mono<Product> deleteByNo(String productNo);

}
