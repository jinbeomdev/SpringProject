package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RequestCreateHistoryTagsDto {
    List<HistoryTag> tags;

    List<HistoryTag> toEntity() {
        return tags;
    }
}
