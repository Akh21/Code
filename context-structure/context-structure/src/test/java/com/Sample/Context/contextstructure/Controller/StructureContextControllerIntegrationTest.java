package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Model.StructureContext;
import com.Sample.Context.contextstructure.Model.StructureContextEntityResponse;
import com.Sample.Context.contextstructure.Service.StructureContextService;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StructureContextControllerIntegrationTest {

@Autowired
GraphQlTester graphQlTester;
@Autowired
GraphQLTestTemplate graphQLTestTemplate;
@MockBean
StructureContextService structureContextService;

    private final MonoGraphQLClient monoGraphQLClient;

    public StructureContextControllerIntegrationTest(@LocalServerPort Integer port) {
        WebClient webClient = WebClient.create("http://localhost:" + port.toString() + "/graphql");
        this.monoGraphQLClient = MonoGraphQLClient.createWithWebClient(webClient);
    }

        @Test
    public void createStructureContextTest()  {


        String structureContextId="defwe";
        String STRUCTURE_CONTEXT_TYPE="dewe";
        String PARENT_TEMPLATE_ID="dwe";
        String STRUCTURE_CONTEXT_TAGS="RF";



//language=graphql

        String mutation = """
    mutation {
    createStructureContext
     (
    structureContext: {"""
                + "structureContextId:\"" + structureContextId + "\", "
                + "structureContextType:\"" + STRUCTURE_CONTEXT_TYPE + "\", "
                + "parentTemplateId:\"" + PARENT_TEMPLATE_ID + "\", "
                + "tags:\"" + STRUCTURE_CONTEXT_TAGS + "\"" +

                """ 
                    }) { structureContextId structureContextType parentTemplateId  tags
                    createdBy  updatedBy  }}""";



//GraphQLResponse response=graphQLTestTemplate.postForResource("graphql/createStructureContext.graphql");

GraphQLResponse graphQLResponse= monoGraphQLClient.reactiveExecuteQuery(mutation).block();

            assert graphQLResponse != null;
            List<?> structureContextID = graphQLResponse.extractValueAsObject("createStructureContext[*].structureContextId", List.class);
            assertTrue(structureContextId.contains("defwe"));

    }
    @Test
    public void findAllStructureContextTest(){
        StructureContext structureContext=new StructureContext();
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

        GraphQLResponse graphQLResponse=monoGraphQLClient.reactiveExecuteQuery(document).block();

        graphQlTester.document(document)
                .variable("structureContextId","sd")
                .execute()
                .path("data.getAllStructureContext[*]")
                .entityList(StructureContext.class)
                .get();
        StructureContextEntityResponse structureContextEntityResponse = graphQLResponse.extractValueAsObject("data.getAllStructureContext", StructureContextEntityResponse.class);
//        Assertions.assertNotNull(graphQLResponse);
//        Assertions.assertNotNull(structureContextEntityResponse);
//        Assertions.assertNotNull(structureContextEntityResponse.getStructureContextId());

    }
    @Test
    public void getByIdStructureContextTest(){
        String structureContextId= "scd";
        StructureContextEntityResponse structureContextEntityResponse=StructureContextEntityResponse.builder()
                .structureContextId("scd")
                .build();

//language=graphql
        String document= """
        query getById($structureContextId:String){
        getById(structureContextId: $structureContextId ){

        structureContextId


        }}

        """;
//        graphQlTester.document(document)
//                .variable("structureContextId","scd")
//                .execute()
//                .path("data.getById.structureContextId")
//                .entity(String.class)
//                .isEqualTo("scd");
        GraphQLResponse graphQLResponse=monoGraphQLClient.reactiveExecuteQuery(document).block();


        StructureContextEntityResponse structureContextEntityResponse1 = graphQLResponse.extractValueAsObject("data.getById", StructureContextEntityResponse.class);


        Assertions.assertNotNull(structureContextEntityResponse);
        Assertions.assertNotNull(structureContextEntityResponse.getStructureContextId());
    }

}
