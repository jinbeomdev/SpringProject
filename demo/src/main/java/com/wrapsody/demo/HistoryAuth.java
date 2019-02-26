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
public class HistoryAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyAuthId;

    @NotNull
    @Column
    private String historyAuthName;

    @NotNull
    @Column
    private Boolean historyAuthType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "history_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private History history;

    @Builder
    HistoryAuth(String historyAuthName, Boolean historyAuthType, History history) {
        this.historyAuthName = historyAuthName;
        this.historyAuthType = historyAuthType;
        this.history = history;
    }
}
