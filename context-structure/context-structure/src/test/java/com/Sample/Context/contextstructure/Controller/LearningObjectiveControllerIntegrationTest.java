package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Service.LearningObjectiveService;
import com.Sample.Context.contextstructure.Service.StructureContextService;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import com.netflix.graphql.dgs.client.GraphQLResponse;
import com.netflix.graphql.dgs.client.MonoGraphQLClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LearningObjectiveControllerIntegrationTest {
    @Autowired
    GraphQlTester graphQlTester;
    @Autowired
    GraphQLTestTemplate graphQLTestTemplate;
    @MockBean
    LearningObjectiveService learningObjectiveService;

    private final MonoGraphQLClient monoGraphQLClient;

    public LearningObjectiveControllerIntegrationTest(@LocalServerPort Integer port) {
        WebClient webClient = WebClient.create("http://localhost:" + port.toString() + "/graphql");
        this.monoGraphQLClient = MonoGraphQLClient.createWithWebClient(webClient);
    }

@Test
    public  void createLearningObjectiveTest(){

        String learningObjectiveId="rrr";
        String CONTEXT_ID="vcdf";
        String CONTEXT_TYPE="etg";
        String CONTEXT_VERSION="eve";

        String mutation = """
    mutation {
    createLearningObjective
     (
    createLearningObjective: {"""
                + "learningObjectiveId:\"" + learningObjectiveId + "\", "
                + "contextId:\"" + CONTEXT_ID + "\", "
                + "contextType:\"" + CONTEXT_TYPE + "\", "
                + "contextVersion:\"" + CONTEXT_VERSION + "\"" +

                """ 
                    }) { learningObjectiveId contextId contextType contextVersion  }}""";

        GraphQLResponse graphQLResponse= monoGraphQLClient.reactiveExecuteQuery(mutation).block();

//        assert graphQLResponse != null;
//        List<?> learningObjectiveID = graphQLResponse.extractValueAsObject("data.createLearningObjective[*].learningObjectiveId", List.class);
//        assertTrue(learningObjectiveID.contains("rrr"));

    }
}
