package com.wrapsody.demo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class HistoryAuth {
    @Id
    @GeneratedValue
    private Long historyAuthId;

    @Column
    private  String historyAuthName;

    @Builder
    HistoryAuth(String historyAuthName) {
        this.historyAuthName = historyAuthName;
    }
}
