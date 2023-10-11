package com.Sample.Context.contextstructure.Model;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class StructureNodeRequest {
    private UUID id;
    private String nodeType;
    private String nodeId;
    private String title;
    private boolean hidden;

}
