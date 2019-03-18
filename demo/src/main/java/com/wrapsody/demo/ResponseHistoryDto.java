package com.wrapsody.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResponseHistoryDto {
    private History history;
    private List<HistoryTag> tags;
    private List<HistoryAuth> auths;
}
