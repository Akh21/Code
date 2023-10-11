package com.Sample.Context.contextstructure.Mapper;

import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.Model.StructureNode;
import com.Sample.Context.contextstructure.Model.StructureNodeResponse;
import com.Sample.Context.contextstructure.Model.Toc;
import com.Sample.Context.contextstructure.Model.TocResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class EntityToDto {
    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private EntityToDto(){

    }
    public static StructureContextEntityResponse map(StructureContext structureContext){
        return StructureContextEntityResponse.builder()
                .id(structureContext.getId())
                .parentContextId(structureContext.getParentContextId())
                .parentContextVersion(structureContext.getParentContextVersion())
                .parentTemplateId(structureContext.getParentTemplateId())
                .parentTemplateVersion(structureContext.getParentTemplateVersion())
                .productPlatform(structureContext.getProductPlatform())
                .structureContextId(structureContext.getStructureContextId())
                .structureContextType(structureContext.getStructureContextType())
                .structureContextVersion(structureContext.getStructureContextVersion())
                .parentContextId(structureContext.getParentContextVersion())
                .tocs(mapper.convertValue(structureContext.getToc(), Toc.class))
                .tags(structureContext.getTags())
                .createdBy(structureContext.getCreatedBy())
                .createdAt(structureContext.getCreatedAt())
                .updatedBy(structureContext.getUpdatedBy())
                .updatedAt(structureContext.getUpdatedAt())
                .build();
    }
    public static StructureNodeResponse map(StructureNode structureNode){
        return StructureNodeResponse.builder()
                .id(structureNode.getId())
                .title(structureNode.getTitle())
                .nodeId(structureNode.getNodeId())
                .nodeType(structureNode.getNodeType())
                .hidden(structureNode.isHidden())
                .build();
    }
    public static LearningObjectiveResponse map(LearningObjective learningObjective) {
        return LearningObjectiveResponse.builder()
                .learningObjectiveId(learningObjective.getLearningObjectiveId())
                .contextId(learningObjective.getContextId())
                .contextVersion(learningObjective.getContextVersion())
                .contextType(learningObjective.getContextType())
                .title(learningObjective.getTitle())
                .build();
    }

//    public static TocResponse map(Toc toc){
//        return TocResponse.builder()
//                .structureNodes(toc.getStructureNodes())
//                .contextType(toc.getContextType())
//                .contextVersion(toc.getContextVersion())
//                .contextId(toc.getContextId())
//                .title(toc.getTitle())
//                .build();
//    }

}
