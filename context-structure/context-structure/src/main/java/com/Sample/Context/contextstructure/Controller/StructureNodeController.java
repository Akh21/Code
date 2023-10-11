package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Service.StructureNodeService;
import com.Sample.Context.contextstructure.Model.StructureNode;
import com.Sample.Context.contextstructure.Model.StructureNodeRequest;
import com.Sample.Context.contextstructure.Model.StructureNodeResponse;
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
public class StructureNodeController {
    @Autowired
    StructureNodeService structureNodeService;
    public static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    @QueryMapping("getAllNodes")
    public Flux<StructureNode> getAll(){
        Flux<StructureNode> structureNodeFlux=structureNodeService.findAll();
        System.out.println(structureNodeFlux);
        return structureNodeFlux;
    }
    @MutationMapping("createStructureNode")
    Mono<StructureNodeResponse> CreateStructureNode(@Argument StructureNodeRequest structureNode){

        System.out.println(structureNode.getNodeId());
        System.out.println(structureNode.getNodeType());
        System.out.println(structureNode.getTitle());
        System.out.println(structureNode.isHidden());

        return structureNodeService.addStructureNode(structureNode);
    }
}
