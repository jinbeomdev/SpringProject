package com.wrapsody.demo;

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
    @Column(unique = true)
    private String historyMasterName;

    @NotNull
    @Column
    private String historyPreSetName;

    @Builder
    History(String historyMasterName, String historyPreSetName) {
        this.historyMasterName = historyMasterName;
        this.historyPreSetName = historyPreSetName;
    }
}
