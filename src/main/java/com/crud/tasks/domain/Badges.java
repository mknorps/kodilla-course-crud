package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Badges {

    private int votes;

    @JsonProperty("attachmentsByType")
    private AttachmentsByType attachments;
}
