package com.Sample.Context.contextstructure.Service;

import com.Sample.Context.contextstructure.Mapper.EntityToDto;
import com.Sample.Context.contextstructure.Model.StructureContext;
import com.Sample.Context.contextstructure.Model.StructureContextEntityRequest;
import com.Sample.Context.contextstructure.Model.StructureContextEntityResponse;
import com.Sample.Context.contextstructure.Model.Toc;
import com.Sample.Context.contextstructure.Repository.StructureContextRepository;
import com.Sample.Context.contextstructure.ScalarImpl.Scalar;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.AutoConfigureGraphQl;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

import static org.mockito.Mockito.when;

@GraphQlTest(StructureContextService.class)
@Import({Scalar.class})
@AutoConfigureGraphQl
public class StructureContextServiceTest {
    @Autowired
    GraphQlTester graphQlTester;
    @MockBean
    StructureContextRepository structureContextRepository;

    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Test
    public void createStructureContextTest() {
        Toc toc = new Toc();

        StructureContextEntityRequest structureContextEntityRequest = StructureContextEntityRequest.builder()
                .parentContextId("adasd")
                .structureContextId("fdsv")
                .structureContextType("sdfsef")
                .structureContextVersion("fef")
                .parentContextVersion("fesf")
                .parentContextId("fsf")
                .productPlatform("sefef")
                .parentTemplateId("dsfse")
                .parentTemplateVersion("esdes")
                .updatedBy("sdc")
                .createdBy("sada")
                .tocs(toc)
                .tags(Map.of("srsfs", "desfe", "de", "efwef"))
                .build();


        StructureContext structureContext = StructureContext.builder()
                .parentContextId(structureContextEntityRequest.getParentContextId())
                .parentContextVersion(structureContextEntityRequest.getParentContextVersion())
                .parentTemplateId(structureContextEntityRequest.getParentTemplateId())
                .parentTemplateVersion(structureContextEntityRequest.getParentTemplateVersion())
                .productPlatform(structureContextEntityRequest.getProductPlatform())
                .structureContextId(structureContextEntityRequest.getStructureContextId())
                .structureContextType(structureContextEntityRequest.getStructureContextType())
                .structureContextVersion(structureContextEntityRequest.getStructureContextVersion())
                .parentContextId(structureContextEntityRequest.getParentContextVersion())
                .toc(mapper.convertValue(structureContextEntityRequest.getTocs(), Map.class))
                .tags(structureContextEntityRequest.getTags())
                .createdBy(structureContextEntityRequest.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .updatedBy(structureContextEntityRequest.getUpdatedBy())
                .updatedAt(LocalDateTime.now())
                .build();

        StructureContextEntityResponse structureContextEntityResponse = StructureContextEntityResponse.builder()
                .parentContextId("adasd")
                .structureContextId("fdsv")
                .structureContextType("sdfsef")
                .structureContextVersion("fef")
                .parentContextVersion("fesf")
                .parentContextId("fsf")
                .productPlatform("sefef")
                .parentTemplateId("dsfse")
                .parentTemplateVersion("esdes")
                .updatedBy("sdc")
                .createdBy("sada")
                .tocs(toc)
                .tags(Map.of("srsfs", "desfe", "de", "efwef"))
                .build();
        when(structureContextRepository.save(structureContext))
                .thenReturn(Mono.just(structureContext));

//language=graphql
        String query = """
            mutation
            createStructureContext($structureContextEntityRequest:StructureContextEntityRequest!){
            createStructureContext(structureContext: $structureContextEntityRequest)
            {
            structureContextId

            }
            }
            """;
//        graphQlTester.document(query)
//                .variable("structureContextEntityRequest",Map.of("structureContextId","gv"))
//                .execute()
//                .path("data.createStructureContext.structureContextId")
//                .entity(String.class)
//                .isEqualTo("gv");


    }
    @Test
    public void  findAllTest(){
        String document= """
                query {
                getAllStructureContext {
                structureContextType
                structureContextVersion
                structureContextVersion
                structureContextId
                tags
                parentTemplateVersion
                parentTemplateId
                productPlatform
                parentContextVersion
                parentContextId
                tags
                toc
                updatedBy
                updatedAt
                createdBy
                createdAt

                                }
                }
                """;
        graphQlTester.document(document)
                .variable("se","sd")
                .execute()
                .path("data.getAllStructureContext[*]")
                .entityList(StructureContext.class)
                .get();

    }
    @Test
    public void findAllNegativeTest(){
        when(structureContextRepository.findAll()).thenReturn(Flux.empty());
//language=graphql
        String document= """
query {
getAllStructureContext {
       structureContextType
                structureContextVersion
                structureContextVersion
                structureContextId
                tags
                parentTemplateVersion
                parentTemplateId
                productPlatform
                parentContextVersion
                parentContextId
                tags
                toc
                updatedBy
                updatedAt
                createdBy
                createdAt
        }
}
""";
        graphQlTester.document(document)
                .variable("fs","c")
                .execute()
                .errors()
                .verify();
    }
    @Test
    void getByIdTest(){
        String structureContextId= "scd";
        StructureContext structureContext=StructureContext.builder()
                .structureContextId("scd")
                .build();
//        StructureContextEntityResponse structureContextEntityResponse=StructureContextEntityResponse.builder()
//                .structureContextId("scd")
//                .build();

        when(structureContextRepository.findByStructureContextId("scd"))
                .thenReturn(Mono.just(structureContext));

//language=graphql
        String document= """
        query getById($structureContextId:String){
        getById(structureContextId: $structureContextId ){

        structureContextId


        }}

        """;
        graphQlTester.document(document)
                .variable("structureContextId","scd")
                .execute();
//                .path("data.getById.structureContextId")
//                .entity(String.class)
//                .isEqualTo("scd");
    }
    }

