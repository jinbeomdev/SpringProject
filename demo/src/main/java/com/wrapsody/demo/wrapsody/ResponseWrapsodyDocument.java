package com.wrapsody.demo.wrapsody;

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
public class ResponseWrapsodyDocument {
    private String fileName;
    private boolean viewAuthAllUsers;
    List<HistoryAuth> authList = new ArrayList<HistoryAuth>();
    List<HistoryTag> tagList = new ArrayList<HistoryTag>();
}