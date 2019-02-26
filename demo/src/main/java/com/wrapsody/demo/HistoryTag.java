package com.wrapsody.demo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class HistoryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyTagId;

    @NotNull
    @Column
    private String historyTagName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "history_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private History history;

    @Builder
    HistoryTag(String historyTagName, History history) {
        this.historyTagName = historyTagName;
        this.history = history;
    }
}