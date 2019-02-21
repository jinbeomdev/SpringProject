package com.wrapsody.demo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class Document {
    private String documentName;
    private List<HistoryTag> tags;
    private List<HistoryAuth> auths;

    @Builder
    Document(String documentName, List<HistoryTag> tags, List<HistoryAuth> auths) {
        this.documentName = documentName;
        this.tags = tags;
        this.auths = auths;
    }
}
