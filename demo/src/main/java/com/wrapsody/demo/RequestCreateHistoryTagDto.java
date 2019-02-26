package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestCreateHistoryTagDto {
    private String historyTagName;

    HistoryTag toEntity(History history) {
        return HistoryTag.builder()
                .historyTagName(historyTagName)
                .build();
    }
}
