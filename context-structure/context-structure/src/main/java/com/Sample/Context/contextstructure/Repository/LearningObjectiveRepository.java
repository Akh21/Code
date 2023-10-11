package com.Sample.Context.contextstructure.Repository;

import com.Sample.Context.contextstructure.Model.LearningObjective;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface LearningObjectiveRepository extends R2dbcRepository<LearningObjective,String> {
    @Query("SELECT * FROM learning_objective WHERE learning_objective_id=:learningObjectiveId AND context_id=:contextId AND context_type=:contextType;")
    Mono<LearningObjective> findByLearningObjective(String learningObjectiveId, String contextId, String contextType );
}
