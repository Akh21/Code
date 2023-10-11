package com.Sample.Context.contextstructure.Mapper;

import com.Sample.Context.contextstructure.Model.*;
import com.Sample.Context.contextstructure.Model.StructureNode;
import com.Sample.Context.contextstructure.Model.StructureNodeRequest;
import com.Sample.Context.contextstructure.Model.Toc;
import com.Sample.Context.contextstructure.Model.TocRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Map;

public class DtoToEntity {
    private static ObjectMapper mapper = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    private DtoToEntity(){
    }
    public static StructureContext map(StructureContextEntityRequest structureContextEntityRequest){
        return StructureContext.builder()
                .parentContextId(structureContextEntityRequest.getParentContextId())
                .parentContextVersion(structureContextEntityRequest.getParentContextVersion())
                .parentTemplateId(structureContextEntityRequest.getParentTemplateId())
                .parentTemplateVersion(structureContextEntityRequest.getParentTemplateVersion())
                .productPlatform(structureContextEntityRequest.getProductPlatform())
                .structureContextId(structureContextEntityRequest.getStructureContextId())
                .structureContextType(structureContextEntityRequest.getStructureContextType())
                .structureContextVersion(structureContextEntityRequest.getStructureContextVersion())
                .parentContextId(structureContextEntityRequest.getParentContextVersion())
                .toc(mapper.convertValue(structureContextEntityRequest.getTocs(), Map.class))
                .tags(structureContextEntityRequest.getTags())
                .createdBy(structureContextEntityRequest.getCreatedBy())
                .createdAt(LocalDateTime.now())
                .updatedBy(structureContextEntityRequest.getUpdatedBy())
                .updatedAt(LocalDateTime.now())
                .build();
    }
    public static StructureNode map(StructureNodeRequest structureNodeRequest){
        return StructureNode.builder()
                .nodeId(structureNodeRequest.getNodeId())
                .title(structureNodeRequest.getTitle())
                .nodeType(structureNodeRequest.getNodeType())
                .hidden(structureNodeRequest.isHidden())
                .build();
    }

    public static LearningObjective map(LearningObjectiveRequest learningObjectiveRequest) {
        return LearningObjective.builder()
                .learningObjectiveId(learningObjectiveRequest.getLearningObjectiveId())
                .contextId(learningObjectiveRequest.getContextId())
                .contextVersion(learningObjectiveRequest.getContextVersion())
                .contextType(learningObjectiveRequest.getContextType())
                .title(learningObjectiveRequest.getTitle())
                .build();
    }
//    public static Toc map(TocRequest tocRequest){
//        return Toc.builder()
//                .title(tocRequest.getTitle())
//                .contextType(tocRequest.getContextType())
//                .contextId(tocRequest.getContextId())
//                .contextVersion(tocRequest.getContextVersion())
//                .build();
//
//    }
}
