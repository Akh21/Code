package com.Sample.Context.contextstructure.Model;

import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class StructureContext {
    @Id
    private UUID id;
    private String structureContextType;
    private String structureContextId;
    private  String structureContextVersion;
    private String parentTemplateId;
    private String parentTemplateVersion;
    private String productPlatform;
    private String parentContextId;
    private String parentContextVersion;
    private Map<String,Object> toc;
    private Map<String,Object> tags;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;

}
