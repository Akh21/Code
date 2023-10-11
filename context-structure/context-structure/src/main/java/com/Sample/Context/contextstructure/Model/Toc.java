package com.Sample.Context.contextstructure.Model;

import com.Sample.Context.contextstructure.Model.StructureNode;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Toc {

    private String contextId;
    private String contextType;
    private String contextVersion;
    private String title;

    private List<StructureNode> structureNodes;



}
