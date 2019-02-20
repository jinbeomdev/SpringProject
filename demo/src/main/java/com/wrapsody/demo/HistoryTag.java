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
public class HistoryTag {
    @Id
    @GeneratedValue
    private Long historyTagId;

    @Column
    private String historyTagName;

    @Builder
    HistoryTag(String historyTagName) {
        this.historyTagName = historyTagName;
    }
}
