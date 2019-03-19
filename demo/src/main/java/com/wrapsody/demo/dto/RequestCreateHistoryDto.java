package com.wrapsody.demo.dto;

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

    public String getTagsAsString() {
        String ret = "";
        for (HistoryTag tag : tags) {
            ret += tag.getHistoryTagName() + ",";
        }
        return ret.substring(0, ret.length() - 1);
    }

    public String getMasterIdAsXml() {
        return new String("<userId>" + Base64Utils.encodeToString(historyMasterId.getBytes()) + "</userId>");
    }

    public String getCheckoutUserIdsAsXml() {
        String ret = "";
        for(HistoryAuth auth : auths) {
            if(auth.getHistoryAuthType() == HistoryAuthType.REVISION) {
                ret += ("<userId>" + Base64Utils.encodeToString(auth.getHistoryAuthId().getBytes()) + "</userId>");
            }
        }
        return ret;
    }

    public String getCheckoutDeptCodesAsXml() {
        String ret = "";
        for(HistoryAuth auth : auths) {
            if(auth.getHistoryAuthType() == HistoryAuthType.DEPT_REVISION) {
                ret += ("<deptCode>" + Base64Utils.encodeToString(auth.getHistoryAuthId().getBytes()) + "</deptCode>");
            }
        }
        return ret;
    }

    public String getViewUserIdsAsXml() {
        String ret = "";
        for(HistoryAuth auth : auths) {
            if(auth.getHistoryAuthType() == HistoryAuthType.READ) {
                ret += ("<userId>" + Base64Utils.encodeToString(auth.getHistoryAuthId().getBytes()) + "</userId>");
            }
        }
        return ret;
    }

    public String getViewDeptCodes() {
        String ret = "";
        for(HistoryAuth auth : auths) {
            if(auth.getHistoryAuthType() == HistoryAuthType.DEPT_READ) {
                ret += ("<deptCode>" + Base64Utils.encodeToString(auth.getHistoryAuthId().getBytes()) + "</deptCode>");
            }
        }
        return ret;
    }
}

