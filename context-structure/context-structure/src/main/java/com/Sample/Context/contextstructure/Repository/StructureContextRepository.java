package com.Sample.Context.contextstructure.Repository;

import com.Sample.Context.contextstructure.Model.StructureContext;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface StructureContextRepository extends R2dbcRepository<StructureContext, UUID> {

@Query("SELECT * FROM structure_context WHERE structure_context_id=:structureContextId;")
    Mono<StructureContext> findByStructureContextId(String structureContextId);
}
