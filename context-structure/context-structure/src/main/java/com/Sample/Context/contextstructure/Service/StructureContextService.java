package com.Sample.Context.contextstructure.Service;

import com.Sample.Context.contextstructure.Mapper.DtoToEntity;
import com.Sample.Context.contextstructure.Mapper.EntityToDto;
import com.Sample.Context.contextstructure.Model.StructureContext;
import com.Sample.Context.contextstructure.Model.StructureContextEntityRequest;
import com.Sample.Context.contextstructure.Model.StructureContextEntityResponse;
import com.Sample.Context.contextstructure.Repository.StructureContextRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StructureContextService {
    @Autowired
    private StructureContextRepository structureContextRepository;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public Flux<StructureContext> findAll(){
        return structureContextRepository.findAll();
    }

    public Mono<StructureContextEntityResponse> findById(String structureContextId){
        return structureContextRepository.findByStructureContextId(structureContextId)
                .map(EntityToDto::map);
    }
    public Mono<StructureContextEntityResponse> addStructureContext(StructureContextEntityRequest structureContext){
return structureContextRepository.save(DtoToEntity.map(structureContext))
        .map(EntityToDto::map);
    }

}
