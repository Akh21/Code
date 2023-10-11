package com.Sample.Context.contextstructure.Model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class StructureContextEntityRequest {
    private String structureContextType;
    private String structureContextId;
    private  String structureContextVersion;
    private String parentTemplateId;
    private String parentTemplateVersion;
    private String productPlatform;
    private String parentContextId;
    private String parentContextVersion;
    private Toc tocs;
    private Map<String,Object> tags;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
}
