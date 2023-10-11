package com.Sample.Context.contextstructure.Repository;

import com.Sample.Context.contextstructure.Model.StructureNode;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface StructureNodeRepository extends R2dbcRepository<StructureNode, UUID> {


}
