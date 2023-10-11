package com.Sample.Context.contextstructure.Service;

import com.Sample.Context.contextstructure.Mapper.DtoToEntity;
import com.Sample.Context.contextstructure.Mapper.EntityToDto;
import com.Sample.Context.contextstructure.Repository.StructureNodeRepository;
import com.Sample.Context.contextstructure.Model.StructureNode;
import com.Sample.Context.contextstructure.Model.StructureNodeRequest;
import com.Sample.Context.contextstructure.Model.StructureNodeResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StructureNodeService {
    @Autowired
    StructureNodeRepository structureNodeRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public Flux<StructureNode> findAll(){
        return structureNodeRepository.findAll();
    }

    public Mono<StructureNodeResponse> addStructureNode(StructureNodeRequest structureNode){
        return structureNodeRepository.save(DtoToEntity.map(structureNode))
                .map(EntityToDto::map);
    }

}
