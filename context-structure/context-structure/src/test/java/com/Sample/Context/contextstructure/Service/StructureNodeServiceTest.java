package com.Sample.Context.contextstructure.Service;

import com.Sample.Context.contextstructure.Model.StructureNode;
import com.Sample.Context.contextstructure.Model.StructureNodeRequest;
import com.Sample.Context.contextstructure.Model.StructureNodeResponse;
import com.Sample.Context.contextstructure.Repository.StructureContextRepository;
import com.Sample.Context.contextstructure.Repository.StructureNodeRepository;
import com.Sample.Context.contextstructure.ScalarImpl.Scalar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.AutoConfigureGraphQl;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@GraphQlTest(StructureNodeService.class)
@Import({Scalar.class})
@AutoConfigureGraphQl
public class StructureNodeServiceTest {

    @Autowired
    GraphQlTester graphQlTester;
    @MockBean
    StructureNodeRepository structureNodeRepository;

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

        when(structureNodeRepository.save(structureNode))
                .thenReturn(Mono.just(structureNode));

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
        when(structureNodeRepository.findAll()).thenReturn(Flux.empty());
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
