package com.wrapsody.demo.history.dto;

import com.wrapsody.demo.history.History;
import com.wrapsody.demo.history.HistoryAuth;
import com.wrapsody.demo.history.HistoryTag;
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
        setHistoryIsFavorite(history.getHistoryIsFavorite());

        this.tags = tags;
        this.auths = auths;
    }
}
