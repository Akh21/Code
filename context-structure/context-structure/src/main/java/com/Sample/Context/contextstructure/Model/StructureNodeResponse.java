package com.Sample.Context.contextstructure.Model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StructureNodeResponse {
    private UUID id;
    private String nodeType;
    private String nodeId;
    private String title;
    private boolean hidden;
    private List<StructureNode> structureNodes;


}
