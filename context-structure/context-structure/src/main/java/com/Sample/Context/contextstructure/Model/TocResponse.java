package com.Sample.Context.contextstructure.Model;
;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TocResponse {
    private String contextType;
    private String contextId;
    private String contextVersion;
    private String title;
    private List<StructureNode> structureNodes;
}
