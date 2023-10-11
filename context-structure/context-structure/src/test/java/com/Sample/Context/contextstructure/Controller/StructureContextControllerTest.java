package com.Sample.Context.contextstructure.Controller;
import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.ScalarImpl.Scalar;
import com.Sample.Context.contextstructure.Service.StructureContextService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.AutoConfigureGraphQl;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.data.jpa.domain.JpaSort.path;


@GraphQlTest(StructureContextController.class)
@Import({Scalar.class})

public class StructureContextControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockBean

    StructureContextService structureContextService;
    private static final ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Test
    public void createStructureContextTest() {
        StructureNode structureNode=StructureNode.builder()
                .nodeId("cds")
                .nodeType("dds")
                .title("dvd")
                .hidden(false).build();

    Toc toc =  Toc.builder()
            .contextId("ed")
            .contextVersion("ad")
            .contextType("ada")
            .title("da")
            .structureNodes(List.of(structureNode))
            .build();

    StructureContextEntityRequest structureContextEntityRequest = StructureContextEntityRequest.builder()
            .parentContextId("asd")
            .structureContextId("fv")
            .structureContextType("sdf")
            .structureContextVersion("fef")
            .parentContextVersion("fsf")
            .parentContextId("fsf")
            .productPlatform("se")
            .parentTemplateId("dse")
            .parentTemplateVersion("des")
            .updatedBy("sdc")
            .createdBy("sada")
            .tocs(toc)
            .tags(Map.of("srs", "des", "de", "efw"))
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
            .updatedBy(structureContextEntityRequest.getUpdatedBy())
            .build();

    StructureContextEntityResponse structureContextEntityResponse = StructureContextEntityResponse.builder()
            .parentContextId("asd")
            .structureContextId("fsv")
            .structureContextType("sef")
            .structureContextVersion("fef")
            .parentContextVersion("fsf")
            .parentContextId("fsf")
            .productPlatform("sef")
            .parentTemplateId("dse")
            .parentTemplateVersion("des")
            .updatedBy("sdc")
            .createdBy("sada")
            .tocs(toc)
            .tags(Map.of("srs", "dee", "de", "eff"))
            .build();


    when(structureContextService.addStructureContext(structureContextEntityRequest))
            .thenReturn(Mono.just(structureContextEntityResponse));



        String query = """
                mutation createStructureContext($structureContextEntityRequest: StructureContextEntityRequest!)
                { createStructureContext(structureContext:$structureContextEntityRequest){structureContextId}}""";

//        String query= """
//            mutation {createStructureContext(structureContext: {
//            structureContextType: \"sdvf\" structureContextId: \"esdds\" structureContextVersion: \"aeda\"
//             parentContextId: \"dad\" parentContextVersion: \"adasd\" parentTemplateId: \"agg\" parentTemplateVersion: \"aede\"
//             productPlatform: \"crs\" tocs: {contextId: \"sdc\" contextType: \"wfc\" contextVersion: \"weew\"
//             title: \"sdf\" structureNodes: {nodeId: \"dfv\" nodeType: \"sc\" title: \"ss\" }} tags:{} createdBy: \"sfds\" updatedBy: \"sfs\"
//             })
//             {
//             structureContextId
//
//             }}
//            """;

//        StructureContextEntityResponse structureContext1=graphQlTester.document(query)
//
    graphQlTester.document(query)
            .variable("structureContextEntityRequest",Map.of(
//                    "structureContextVersion", "sgh",
//                    "structureContextType", "htf",
                    "structureContextId","esdds"
//                    "parentContextId","fsf",
//                    "parentTemplateVersion","sfr",
//                    "productPlatform","xsx",
//                    "parentContextVersion","xs",
//                    "parentTemplateId","sc"
//
//
//                    "tocs",Map.of("contextId","S",
//                            "contextType","sdd",
//                            "contextVersion","dsds",
//                            "title","ds"
//
//                            )
                    )

            )
            .execute()
            .path("data.createStructureContext.structureContextId")
            .entity(String.class)
            .get();



//            .satisfies(structureContextEntityResponse1 -> {


//    verify(structureContextService,times(1)).addStructureContext(any(StructureContextEntityRequest.class));
//verifyNoMoreInteractions(structureContextService);

}

    @Test

        public void findAllStructureContextTest(){

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
    void findAllStructureContextNegativeTest(){
when(structureContextService.findAll()).thenReturn(Flux.empty());
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
            StructureContextEntityResponse structureContextEntityResponse=StructureContextEntityResponse.builder()
                    .structureContextId("scd")
                    .build();

            when(structureContextService.findById("scd"))
                    .thenReturn(Mono.just(structureContextEntityResponse));

//language=graphql
String document= """
        query getById($structureContextId:String){
        getById(structureContextId: $structureContextId ){

        structureContextId


        }}

        """;
graphQlTester.document(document)
        .variable("structureContextId","scd")
        .execute()
        .path("data.getById.structureContextId")
        .entity(String.class)
        .isEqualTo("scd");

//        .satisfies(structureContextEntityResponse1 -> {
//            assertEquals("svs",structureContextEntityResponse1.getStructureContextId());

//        });
      }
//
      @Test
      void getByIdNegativeTest(){
when(structureContextService.findById("ddfc"))
        .thenReturn(Mono.empty());
//language=graphql
String document= """
 query findById($structureContextId:String){
        getById(structureContextId: $structureContextId ){
        structureContextType
        structureContextId
        structureContextVersion
        tags
        tocs {
        contextId
        contextType
        contextVersion
        structureNodes {
        nodeId
        nodeType

                }
                }
                parentContextVersion
                parentContextId
                productPlatform
                parentTemplateId
                parentTemplateVersion

        }}
""";
graphQlTester.document(document)
        .variable("structureContextId","ddfc")
        .execute()
        .errors()
        .verify();

      }
}


