package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.ScalarImpl.Scalar;
import com.Sample.Context.contextstructure.Service.LearningObjectiveService;
import com.Sample.Context.contextstructure.Service.StructureContextService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.mockito.Mockito.when;

@GraphQlTest(LearningObjectiveController.class)
@Import({Scalar.class})
public class LearningObjectiveControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockBean
    LearningObjectiveService learningObjectiveService;

    @Test
    public void createLearningObjectiveTest(){

        LearningObjectiveRequest learningObjectiveRequest=LearningObjectiveRequest.builder()
                .learningObjectiveId("scd")
                .title("dcds")
                .contextId("dscd")
                .contextType("seds")
                .contextVersion("dcds")
                .build();
        LearningObjective learningObjective= LearningObjective.builder()
                .learningObjectiveId(learningObjectiveRequest.getLearningObjectiveId())
                .contextVersion(learningObjectiveRequest.getContextVersion())
                .contextId(learningObjectiveRequest.getContextId())
                .contextType(learningObjectiveRequest.getContextType())
                .title(learningObjectiveRequest.getTitle())
                .build();
        LearningObjectiveResponse learningObjectiveResponse=LearningObjectiveResponse.builder()
                .learningObjectiveId("scd")
                .title("dcds")
                .contextId("dscd")
                .contextType("seds")
                .contextVersion("dcds")
                .build();

        when(learningObjectiveService.addLearningObjective(learningObjectiveRequest))
                .thenReturn(Mono.just(learningObjectiveResponse));

        //language=graphql
        String query = """
                mutation createLearningObjective($learningObjectiveRequest: LearningObjectiveRequest!)
                { createLearningObjective(learningObjective:$learningObjectiveRequest){learningObjectiveId}}""";

        graphQlTester.document(query)
                .variable("learningObjectiveRequest", Map.of(
                        "learningObjectiveId","scd"
                ))
                .execute()
                .path("data.createLearningObjective.learningObjectiveId")
                .entity(String.class)
                .get();

    }
    @Test
    public void getAllLearningObjectiveTest(){
        String document= """
                query {
                getAllLearningObjective {
                
               learningObjectiveId
               contextId
               contextType
               contextVersion
               title

                                }
                }
                """;
        graphQlTester.document(document)
                .variable("learningObjectiveId","sd")
                .execute()
                .path("data.getAllLearningObjective[*]")
                .entityList(LearningObjective.class)
                .get();
    }
    @Test
    public void getAllLearningObjectiveNegativeTest(){
        when(learningObjectiveService.findAll()).thenReturn(Flux.empty());
//language=graphql
        String document= """
query {
getAllLearningObjective {
       learningObjectiveId
               contextId
               contextType
               contextVersion
               title
               
        }
}
""";
        graphQlTester.document(document)
                .variable("learningObjectiveId","c")
                .execute()
                .errors()
                .verify();
    }
@Test
    public void getByIdLearningObjectiveTest(){
        String learningObjectiveId= "scd";

        LearningObjectiveResponse learningObjectiveResponse=LearningObjectiveResponse.builder()
                .learningObjectiveId("scd")
                .build();

        when(learningObjectiveService.findById("scd","dcdcs","sdcds"))
                .thenReturn(Mono.just(learningObjectiveResponse));

//language=graphql
        String document= """
        query getByIdLearningObjective($learningObjectiveId:String){
        getByIdLearningObjective(learningObjectiveId: $learningObjectiveId){

        learningObjectiveId
       
        }}

        """;
        graphQlTester.document(document)
                .variable("learningObjectiveId","scd")
                .execute()
                .path("data.getByIdLearningObjective.learningObjectiveId")
                .entity(String.class)
                .get();
//                .isEqualTo("scd");

    }
    @Test
    public void getByIdLearningObjectiveNegativeTest(){
        when(learningObjectiveService.findById("dsc","fe","fcfcd")).thenReturn(Mono.empty());
        String document= """
query {
getAllLearningObjective {
       learningObjectiveId
               contextId
               contextType
               contextVersion
               title
               
        }
}
""";
        graphQlTester.document(document)
                .variable("learningObjectiveId","ddfc")
                .execute()
                .errors()
                .verify();

    }
}
