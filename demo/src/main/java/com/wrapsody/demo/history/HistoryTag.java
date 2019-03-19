package com.wrapsody.demo.history;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Data
@Entity
public class HistoryTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column
    private String historyTagCode;

    @NotNull
    @Column
    private String historyTagName;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private HistoryTagType historyTagType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "history_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private History history;

    @Builder
    HistoryTag(String historyTagCode,
               String historyTagName,
               HistoryTagType historyTagType,
               History history) {
        this.historyTagCode = historyTagCode;
        this.historyTagName = historyTagName;
        this.historyTagType = historyTagType;
        this.history = history;
    }
}