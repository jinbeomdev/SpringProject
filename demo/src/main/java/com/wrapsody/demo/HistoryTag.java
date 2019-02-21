package com.wrapsody.demo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class HistoryTag {
    @EmbeddedId
    private HistoryTagId history;

    @Column(unique = true)
    private String historyTagName;

    @Data
    @Embeddable
    public class HistoryTagId implements  Serializable {
        @ManyToOne
        @JoinColumn
        History history;
    }
}