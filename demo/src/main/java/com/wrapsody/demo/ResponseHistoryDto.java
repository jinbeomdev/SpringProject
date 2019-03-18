package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResponseHistoryDto extends History {
    private List<HistoryTag> tags;
    private List<HistoryAuth> auths;

    public ResponseHistoryDto(History history, List<HistoryTag> tags, List<HistoryAuth> auths) {
        setId(history.getId());
        setHistoryMasterId(history.getHistoryMasterId());
        setHistoryMasterName(history.getHistoryMasterName());
        setHistoryPreSetName(history.getHistoryPreSetName());
        setHistoryViewAuthAllUsers(history.getHistoryViewAuthAllUsers());
        setHistoryIsDeleted(history.getHistoryIsDeleted());

        this.tags = tags;
        this.auths = auths;
    }
}
