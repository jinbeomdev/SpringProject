package com.wrapsody.demo;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class History {
    @Id
    @GeneratedValue
    private Long historyId;

    @Column
    private String historyMasterName;

    @Column
    private String historyFreeSetName;

    @OneToMany
    @JoinColumn
    private List<HistoryTag> tags = new ArrayList<HistoryTag>();

    @OneToMany
    @JoinColumn
    private List<HistoryAuth> auths = new ArrayList<HistoryAuth>();

    @Builder
    History(String historyMasterName, String historyFreeSetName) {
        this.historyMasterName = historyMasterName;
        this.historyFreeSetName = historyFreeSetName;
    }
}
