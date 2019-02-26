package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RequestCreateHistoryAuthDto {
    private String historyAuthName;
    private Boolean historyAuthType;

    HistoryAuth toEntity(History history) {
        return HistoryAuth.builder()
                .historyAuthName(historyAuthName)
                .historyAuthType(historyAuthType)
                .history(history)
                .build();
    }
}
