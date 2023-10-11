package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.ScalarImpl.Scalar;
import com.Sample.Context.contextstructure.Service.StructureNodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@GraphQlTest(StructureNodeController.class)
@Import({Scalar.class})
public class StructureNodeControllerTest {
    @Autowired
    GraphQlTester graphQlTester;
    @MockBean
    StructureNodeService structureNodeService;

    @Test
    public void createStructureNodeTest() {
        StructureNodeRequest structureNodeRequest = StructureNodeRequest.builder()
                .nodeId("svffv")
                .nodeType("fvfv")
                .title("sss")
                .hidden(false).build();
        StructureNodeResponse structureNodeResponse = StructureNodeResponse.builder()
                .nodeId("svffv")
                .nodeType("fvfv")
                .title("sss")
                .hidden(false).build();
        StructureNode structureNode = StructureNode.builder()
                .nodeId(structureNodeRequest.getNodeId())
                .nodeType(structureNodeRequest.getNodeType())
                .title(structureNodeRequest.getTitle())
                .hidden(structureNodeRequest.isHidden()).build();

        when(structureNodeService.addStructureNode(structureNodeRequest))
                .thenReturn(Mono.just(structureNodeResponse));

        //language=graphql
        String query = """
                mutation createStructureNode($structureNodeRequest: StructureNodeRequest!)
                { createStructureNode(structureNode:$structureNodeRequest){nodeId}}""";

        graphQlTester.document(query)
                .variable("nodeId", "svffv")
                .execute();
//                .path("data.createStructureNode.nodeId")
//                .entity(String.class)
//                .isEqualTo("svffv");


    }

    @Test

    public void findAllStructureNodeTest() {


//language=graphql
        String document = """
                query {
                getAllNodes {
                nodeId
                nodeType
                title
                hidden
                

                                }
                }
                """;
        graphQlTester.document(document)
                .variable("se", "sd")
                .execute()
                .path("data.getAllNodes[*]")
                .entityList(StructureNode.class)
                .get();
    }

    @Test
    void findAllStructureNodeNegativeTest() {
        when(structureNodeService.findAll()).thenReturn(Flux.empty());
//language=graphql
        String document = """
query {
getAllNodes {
                nodeId
                nodeType
                title
                hidden
                

                                }

}
""";
        graphQlTester.document(document)
                .variable("fs","c")
                .execute()
                .errors()
                .verify();

    }


    }
