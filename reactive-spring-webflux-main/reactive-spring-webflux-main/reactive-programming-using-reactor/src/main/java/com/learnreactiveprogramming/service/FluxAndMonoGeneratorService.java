package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;



public class FluxAndMonoGeneratorService {


    public Flux<String> namesFlux(){
        return Flux.fromIterable(List.of("alex","ben","chloe")) //db or a remote service call
        .log();
    }
    public Mono<String> nameMono(){
        return Mono.just("alex")
                .log();
    }
    public Flux<String> namesFlux_map(){
        return Flux.fromIterable(List.of("alex","ben","chloe")) //db or a remote service call
                .map(String::toUpperCase)
               // .map(s -> s.toUpperCase()) // alternate method

                .log();
        public Flux<String> namesFlux_immutability(){
           var namesFlux = Flux.fromIterable(List.of("alex","ben","chloe"));
            namesFlux.map(String::toUpperCase);
            return namesFlux;
        }
    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

            fluxAndMonoGeneratorService.namesFlux()  //give access to flux
                    .subscribe(name -> {
                        System.out.println("Name is : "+ name);
                    });
            fluxAndMonoGeneratorService.nameMono()
                    .subscribe(name ->{
                        System.out.println("Mono name is: "+ name);
                    });
    }
}}
