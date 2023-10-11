package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Service.StructureContextService;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StructureNodeIntegrationTest {
    @Autowired
    GraphQlTester graphQlTester;
    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;
    @MockBean
    StructureContextService structureContextService;

    private final MonoGraphQLClient monoGraphQLClient;

    public StructureNodeIntegrationTest(@LocalServerPort Integer port) {
        WebClient webClient = WebClient.create("http://localhost:" + port.toString() + "/graphql");
        this.monoGraphQLClient = MonoGraphQLClient.createWithWebClient(webClient);
    }
@Test
    public void createStructureNodeTest() {


        String nodeId = "defw";
        String NODE_TYPE = "dewe";
        String TITLE = "dwe";

        //language=graphql
        String mutation = """
                mutation {
                createStructureNode
                 (
                structureNode: {"""
                + "nodeId:\"" + nodeId + "\", "
                + "nodeType:\"" + NODE_TYPE + "\", "
                + "title:\"" + TITLE + "\"" +
                """ 
                        }) { nodeId nodeType title  }}""";


//        assertThat(response.isOk()).isTrue();
//        assertThat(response.get("$.data.createStructureNode.nodeId")).isNotNull();

        GraphQLResponse graphQLResponse = monoGraphQLClient.reactiveExecuteQuery(mutation).block();

        assert graphQLResponse != null;
        List<?> nodeID= graphQLResponse.extractValueAsObject("createStructureNode[*].nodeId", List.class);
        assertTrue(nodeId.contains("defw"));


    }
}
