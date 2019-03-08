package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RequestCreateHistoryAuthDto {
    private String historyAuthId;
    private String historyAuthName;
    private HistoryAuthType historyAuthType;

    HistoryAuth toEntity(History history) {
        return HistoryAuth.builder()
                .historyAuthId(historyAuthId)
                .historyAuthName(historyAuthName)
                .historyAuthType(historyAuthType)
                .history(history)
                .build();
    }
}
