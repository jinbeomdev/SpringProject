package com.wrapsody.demo.wrapsody.dto;

import com.wrapsody.demo.history.HistoryAuth;
import com.wrapsody.demo.history.HistoryAuthType;
import com.wrapsody.demo.history.HistoryTag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Base64Utils;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class RequestWrapsodyDocumentDto {
    private String syncId;
    private Boolean viewAuthAllUsers;
    List<HistoryTag> tags = new ArrayList<>();
    List<HistoryAuth> auths = new ArrayList<>();

    public String getTagsAsString() {
        String ret = "";
        for (HistoryTag tag : tags) {
            ret += tag.getHistoryTagName() + ",";
        }
        return ret.substring(0, ret.length() - 1);
    }

    public String getMasterIdAsXml() {
        return "<userId>" + Base64Utils.encodeToString(auths.get(0).getHistoryAuthName().getBytes()) + "</userId>";
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
