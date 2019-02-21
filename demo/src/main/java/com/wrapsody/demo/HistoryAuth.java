package com.wrapsody.demo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class HistoryAuth {
    enum AuthType { REVISION, READ }

    @EmbeddedId
    private HistoryAuthId history;

    @Column(unique = true)
    private String historyAuthName;

    @Column
    private AuthType historyAuthType;

    @Data
    @Embeddable
    public class HistoryAuthId implements Serializable {
        @ManyToOne
        @JoinColumn
        private History history;
    }
}
