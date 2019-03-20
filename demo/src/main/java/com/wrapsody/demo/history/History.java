package com.wrapsody.demo.history;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class History extends Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String historyMasterId;

    @NotNull
    @Column
    private String historyMasterName;

    @NotNull
    @Column
    private String historyPreSetName;

    @NotNull
    @Column
    private Boolean historyViewAuthAllUsers;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    @JsonIgnore
    private Boolean historyIsDeleted;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean historyIsFavorite;

    @Builder
    public History(String historyMasterId,
            String historyMasterName,
            String historyPreSetName,
            Boolean historyViewAuthAllUsers,
            Boolean historyIsDeleted,
            Boolean historyIsFavorite) {
        this.historyMasterId = historyMasterId;
        this.historyMasterName = historyMasterName;
        this.historyPreSetName = historyPreSetName;
        this.historyViewAuthAllUsers = historyViewAuthAllUsers;
        this.historyIsDeleted = historyIsDeleted;
        this.historyIsFavorite = historyIsFavorite;
    }
}
