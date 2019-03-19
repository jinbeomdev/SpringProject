package com.wrapsody.demo.history.dto;

import com.wrapsody.demo.history.History;
import com.wrapsody.demo.history.HistoryAuth;
import com.wrapsody.demo.history.HistoryAuthType;
import com.wrapsody.demo.history.HistoryTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.Base64Utils;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestCreateHistoryDto {
    private String historyMasterId;
    private String historyMasterName;
    private String historyPreSetName;
    private Boolean historyViewAuthAllUsers;
    private List<HistoryTag> tags;
    private List<HistoryAuth> auths;

    public History toEntity() {
        return History.builder()
                .historyMasterId(historyMasterId)
                .historyMasterName(historyMasterName)
                .historyPreSetName(historyPreSetName)
                .historyViewAuthAllUsers(historyViewAuthAllUsers)
                .historyIsDeleted(false)
                .historyIsFavorite(false)
                .build();
    }

    public History toHistoryEntity() {
        return toEntity();
    }

    public List<HistoryTag> toHistoryTagEntity(History history) {
        for (HistoryTag tag : tags) {
            tag.setHistory(history);
        }
        return tags;
    }

    public List<HistoryAuth> toHistoryAuthEntity(History history) {
        for (HistoryAuth auth : auths) {
            auth.setHistory(history);
        }
        return auths;
    }
}

