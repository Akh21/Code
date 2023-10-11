package com.Sample.Context.contextstructure.Model;
import jakarta.persistence.Id;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LearningObjective {
    @Id
    private String learningObjectiveId;
    @Id
    private String contextId;
    @Id
    private  String contextType;
    private String contextVersion;
    private  String title;
}
