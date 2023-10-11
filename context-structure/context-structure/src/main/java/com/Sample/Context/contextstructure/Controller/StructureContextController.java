package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Model.StructureContext;
import com.Sample.Context.contextstructure.Model.StructureContextEntityRequest;
import com.Sample.Context.contextstructure.Model.StructureContextEntityResponse;
import com.Sample.Context.contextstructure.Service.StructureContextService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@Slf4j
public class StructureContextController {
  @Autowired
    StructureContextService structureContextService;
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @QueryMapping("getAllStructureContext")
    public Flux<StructureContext> getAll(){
        Flux<StructureContext> structureContextEntityFlux=structureContextService.findAll();
        System.out.println(structureContextEntityFlux);
        return structureContextEntityFlux;
    }
    @QueryMapping("getById")
    public Mono<StructureContextEntityResponse> getById (@Argument String structureContextId){
        return structureContextService.findById(structureContextId);
    }

    @MutationMapping("createStructureContext")
    Mono<StructureContextEntityResponse> CreateStructureContext(@Argument StructureContextEntityRequest structureContext){


        System.out.println(structureContext.getStructureContextId());
        System.out.println(structureContext.getStructureContextType());
        System.out.println(structureContext.getStructureContextVersion());
        System.out.println(structureContext.getParentTemplateId());
        System.out.println(structureContext.getParentContextVersion());
        System.out.println(structureContext.getProductPlatform());
        System.out.println(structureContext.getParentContextVersion());
        System.out.println(structureContext.getParentContextId());
        System.out.println(structureContext.getTocs());
        System.out.println(structureContext.getTags());
        System.out.println(structureContext.getCreatedBy());
        System.out.println(structureContext.getCreatedAt());
        System.out.println(structureContext.getUpdatedBy());
        System.out.println(structureContext.getUpdatedAt());


        return structureContextService.addStructureContext(structureContext);
    }

}
