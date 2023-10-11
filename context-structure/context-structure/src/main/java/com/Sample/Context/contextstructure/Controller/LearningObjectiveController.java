package com.Sample.Context.contextstructure.Controller;

import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.Service.LearningObjectiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class LearningObjectiveController {

@Autowired
   private LearningObjectiveService learningObjectiveService;

    @QueryMapping("getAllLearningObjective")
    public Flux<LearningObjective> getAll() {
        Flux<LearningObjective> learningObjectiveFlux = learningObjectiveService.findAll();
        System.out.println(learningObjectiveFlux);
        return learningObjectiveFlux;
    }

    @MutationMapping("createLearningObjective")
    Mono<LearningObjectiveResponse> CreateLearningObjective(@Argument LearningObjectiveRequest learningObjectiveRequest){

        System.out.println(learningObjectiveRequest.getLearningObjectiveId());
        System.out.println(learningObjectiveRequest.getContextId());
        System.out.println(learningObjectiveRequest.getContextType());
        System.out.println(learningObjectiveRequest.getContextVersion());
        System.out.println(learningObjectiveRequest.getTitle());

        return learningObjectiveService.addLearningObjective(learningObjectiveRequest);
    }
    @QueryMapping("getByIdLearningObjective")
    public Mono<LearningObjectiveResponse> getById (@Argument String learningObjectiveId,@Argument String contextId,@Argument String contextType){
        return learningObjectiveService.findById(learningObjectiveId,contextId,contextType);
    }

}
