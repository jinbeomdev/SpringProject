package com.wrapsody.demo.history.dto;

import com.wrapsody.demo.history.History;
import com.wrapsody.demo.history.HistoryTag;
import com.wrapsody.demo.history.HistoryTagType;
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

    public HistoryTag toEntity(History history) {
        return HistoryTag.builder()
                .historyTagCode(historyTagCode)
                .historyTagName(historyTagName)
                .historyTagType(historyTagType)
                .history(history)
                .build();
    }
}
