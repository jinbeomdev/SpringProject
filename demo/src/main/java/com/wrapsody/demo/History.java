package com.wrapsody.demo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
public class History extends Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @NotNull
    @Column
    private String historyMasterName;

    @NotNull
    @Column
    private String historyPreSetName;

    @NotNull
    @Column
    private Boolean historyIsDeleted;

    @Builder
    History(String historyMasterName,
            String historyPreSetName,
            Boolean historyIsDeleted) {
        this.historyMasterName = historyMasterName;
        this.historyPreSetName = historyPreSetName;
        this.historyIsDeleted = historyIsDeleted;
    }
}
