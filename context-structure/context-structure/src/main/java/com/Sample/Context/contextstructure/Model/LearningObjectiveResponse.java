package com.Sample.Context.contextstructure.Model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LearningObjectiveResponse {
    private String learningObjectiveId;
    private String contextId;
    private  String contextType;
    private String contextVersion;
    private  String title;
}
