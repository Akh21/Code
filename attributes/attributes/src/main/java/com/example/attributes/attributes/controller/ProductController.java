package com.example.attributes.attributes.controller;


import com.example.attributes.attributes.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Controller
public class ProductController {

    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @QueryMapping("mono")
    public Mono<String> mono(){
        System.out.println("mono is called");
        log.debug("Executing sayHello...");
        return Mono.just("Hello World!")
                .doOnNext(msg -> log.debug("sayHello executed with: {}", msg));
    }

    @QueryMapping(name = "products")
    public Flux<Product> products() {
        log.info("called");
        return Flux.just(
                Product.builder()
                        .name("Vishruth")
                        .attributes(Map.of(
                                "distance","300km",
                                "cost","10_000_000",
                                "height","10km"
                        ))
                        .build(),
                Product.builder()
                        .name("Joel")
                        .attributes(Map.of(
                                "weight","20kg",
                                "nation","Ukraine",
                                "cost","20_000"
                        ))
                        .build(),
                Product.builder()
                        .name("Chandan")
                        .attributes(Map.of("Weight", "20kg"))
                .build()
        );
    }
}


