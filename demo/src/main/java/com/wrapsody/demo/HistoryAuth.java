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
    @JsonIgnore
    private Long id;

    @NotNull
    @Column
    private String historyAuthId;

    @NotNull
    @Column
    private String historyAuthName;

    @NotNull
    @Column
    @Enumerated(EnumType.STRING)
    private HistoryAuthType historyAuthType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "history_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private History history;

    @Builder
    HistoryAuth(String historyAuthId,
                String historyAuthName,
                HistoryAuthType historyAuthType,
                History history) {
        this.historyAuthId = historyAuthId;
        this.historyAuthName = historyAuthName;
        this.historyAuthType = historyAuthType;
        this.history = history;
    }
}
