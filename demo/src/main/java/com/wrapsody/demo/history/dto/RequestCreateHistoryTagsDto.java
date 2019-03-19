package com.wrapsody.demo.history.dto;

import com.wrapsody.demo.history.HistoryTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RequestCreateHistoryTagsDto {
    List<HistoryTag> tags;

    public List<HistoryTag> toEntity() {
        return tags;
    }
}
