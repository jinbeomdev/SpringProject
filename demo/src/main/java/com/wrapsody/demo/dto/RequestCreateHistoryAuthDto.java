package com.wrapsody.demo.dto;

import com.wrapsody.demo.history.History;
import com.wrapsody.demo.history.HistoryAuth;
import com.wrapsody.demo.history.HistoryAuthType;
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

    public HistoryAuth toEntity(History history) {
        return HistoryAuth.builder()
                .historyAuthId(historyAuthId)
                .historyAuthName(historyAuthName)
                .historyAuthType(historyAuthType)
                .history(history)
                .build();
    }
}
