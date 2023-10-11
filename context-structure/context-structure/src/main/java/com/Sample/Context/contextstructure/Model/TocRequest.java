package com.Sample.Context.contextstructure.Model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class TocRequest {
    private String contextType;
    private String contextId;
    private String contextVersion;
    private String title;
}
