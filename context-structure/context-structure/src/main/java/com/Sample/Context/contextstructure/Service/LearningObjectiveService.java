package com.Sample.Context.contextstructure.Service;

import com.Sample.Context.contextstructure.Mapper.DtoToEntity;
import com.Sample.Context.contextstructure.Mapper.EntityToDto;
import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.Repository.LearningObjectiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LearningObjectiveService {
    @Autowired
    private LearningObjectiveRepository learningObjectiveRepository;

    public Flux<LearningObjective> findAll(){
        return learningObjectiveRepository.findAll();
    }
    public Mono<LearningObjectiveResponse> addLearningObjective( LearningObjectiveRequest learningObjectiveRequest){
        return learningObjectiveRepository.save(DtoToEntity.map(learningObjectiveRequest))
                .map(EntityToDto::map);
    }

    public Mono<LearningObjectiveResponse> findById(String learningObjectiveId, String contextId, String contextType){
        return learningObjectiveRepository.findByLearningObjective(learningObjectiveId, contextId,contextType)
                .map(EntityToDto::map);
    }

}
