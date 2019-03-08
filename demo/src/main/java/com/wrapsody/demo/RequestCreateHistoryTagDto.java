package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestCreateHistoryTagDto {
    private String historyTagCode;
    private String historyTagName;
    private HistoryTagType historyTagType;

    HistoryTag toEntity(History history) {
        return HistoryTag.builder()
                .historyTagCode(historyTagCode)
                .historyTagName(historyTagName)
                .historyTagType(historyTagType)
                .history(history)
                .build();
    }
}
