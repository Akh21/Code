package com.Sample.Context.contextstructure.Model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class LearningObjectiveRequest {

    private String learningObjectiveId;
    private String contextId;
    private  String contextType;
    private String contextVersion;
    private  String title;
}
