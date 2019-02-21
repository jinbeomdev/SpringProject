package com.wrapsody.demo;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class History {
    @Id
    @GeneratedValue
    private Long historyId;

    @Column(unique = true)
    private String historyMasterName;

    @Column
    private String historyFreeSetName;

    @Column
    @OneToMany(targetEntity = HistoryTag.class, mappedBy = "history", fetch = FetchType.EAGER)
    private Set<HistoryTag> tags = new HashSet<HistoryTag>();

    @Column
    @OneToMany(targetEntity = HistoryAuth.class, mappedBy = "history", fetch = FetchType.EAGER)
    private Set<HistoryAuth> auths = new HashSet<HistoryAuth>();

    public Set<HistoryTag> getTags() {
        return tags;
    }

    public Set<HistoryAuth> getAuths() {
        return auths;
    }

    @Builder
    History(String historyMasterName, String historyFreeSetName) {
        this.historyMasterName = historyMasterName;
        this.historyFreeSetName = historyFreeSetName;
    }
}
