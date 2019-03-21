package com.wrapsody.demo.wrapsody.dto;

import com.wrapsody.demo.history.HistoryAuth;
import com.wrapsody.demo.history.HistoryTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseWrapsodyDocumentDto {
    private String fileName;
    private boolean viewAuthAllUsers;
    List<HistoryTag> tags = new ArrayList<>();
    List<HistoryAuth> auths = new ArrayList<>();
}