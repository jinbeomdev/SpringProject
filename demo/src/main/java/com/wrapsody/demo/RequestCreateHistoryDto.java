package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestCreateHistoryDto {
    private String historyMasterName;
    private String historyPreSetName;
    private List<HistoryTag> tags;
    private List<HistoryAuth> auths;

    History toEntity() {
        return History.builder()
                .historyMasterName(historyMasterName)
                .historyPreSetName(historyPreSetName)
                .historyIsDeleted(true)
                .build();
    }

    /*
    * TODO : history 만들 때, 한 번에 태그와 권한을 설정할 수 있도록 하기
    * */
    History toHistoryEntity() {
        return toEntity();
    }

    List<HistoryTag> toHistoryTagEntity(History history) {
        for (HistoryTag tag: tags) {
            tag.setHistory(history);
        }
        return tags;
    }

    List<HistoryAuth> toHistoryAuthEntity(History history) {
        for (HistoryAuth auth: auths) {
            auth.setHistory(history);
        }
        return auths;
    }
}

