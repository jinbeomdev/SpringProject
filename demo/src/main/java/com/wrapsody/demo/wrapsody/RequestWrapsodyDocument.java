package com.wrapsody.demo.wrapsody;

import com.wrapsody.demo.history.HistoryAuth;
import com.wrapsody.demo.history.HistoryAuthType;
import com.wrapsody.demo.history.HistoryTag;
import com.wrapsody.demo.history.HistoryTagType;
import com.wrapsody.demo.wrapsody.dto.ResponseWrapsodyDocumentDto;
import com.wrapsody.demo.wrapsody.exception.WrapsodyNotFoundException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class RequestWrapsodyDocument extends RequestWrapsody {
    public RequestWrapsodyDocument(String syncId) {
        super("SYNCINFO");
        this.getMap().add("MSG", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<syncinfo_request>\n" +
                "  <ver>1.0</ver>\n" +
                "  <syncId>" + syncId + "</syncId>\n" +
                "  <user>\n" +
                "    <userId>" + USER_ID + "</userId>\n" +
                "    <password>" + PASSWORD + "</password>\n" +
                "  </user>\n" +
                "</syncinfo_request>");

        this.setRequest(new HttpEntity<>(this.getMap(), this.getHttpHeaders()));
        this.setResponse(this.getRestTemplate().postForEntity(URI, this.getRequest(), String.class));
    }

    public ResponseWrapsodyDocumentDto getDocument()
            throws UnsupportedEncodingException,
            WrapsodyNotFoundException {

        if (!statusIsSuccess()) {
            throw new WrapsodyNotFoundException(getFailMsg());
        }

        JSONObject json = XML.toJSONObject(getMsg(), true).getJSONObject("syncinfo_result");

        String fileName = json.getString("filename");

        boolean viewAuthAllUsers = json.getBoolean("viewAuthAllUsers");
        String masterId = new String(Base64Utils.decode(json.getJSONObject("master").getString("userId").getBytes()), StandardCharsets.UTF_8);
        String masterName = new String(Base64Utils.decode(json.getJSONObject("master").getString("userName").getBytes()), StandardCharsets.UTF_8);

        Object authList = json.get("checkoutAuthList");
        Object viewList = json.get("viewAuthList");
        Object tagList = json.get("tagList");

        ResponseWrapsodyDocumentDto responseWrapsodyDocumentDto = new ResponseWrapsodyDocumentDto();
        responseWrapsodyDocumentDto.setFileName(new String(Base64Utils.decode(fileName.getBytes()), StandardCharsets.UTF_8));
        responseWrapsodyDocumentDto.setViewAuthAllUsers(viewAuthAllUsers);
        responseWrapsodyDocumentDto.getAuths().add(HistoryAuth.builder()
                .historyAuthId(masterId)
                .historyAuthName(masterName)
                .historyAuthType(HistoryAuthType.MASTER)
                .build());

        if (!(authList instanceof String)) {
            authList = json.getJSONObject("checkoutAuthList").get("user");
            if (authList instanceof JSONObject) {
                HistoryAuth historyAuth = new HistoryAuth();
                historyAuth.setHistoryAuthId(new String(Base64Utils.decode(((JSONObject) authList).getString("userId").getBytes()), StandardCharsets.UTF_8));
                historyAuth.setHistoryAuthName(new String(Base64Utils.decode(((JSONObject) authList).getString("userName").getBytes()), StandardCharsets.UTF_8));
                historyAuth.setHistoryAuthType(HistoryAuthType.REVISION);
                responseWrapsodyDocumentDto.getAuths().add(historyAuth);
            } else if (authList instanceof JSONArray) {
                List<Object> list = ((JSONArray) authList).toList();
                for (Object object : list) {
                    HistoryAuth historyAuth = new HistoryAuth();
                    historyAuth.setHistoryAuthId(new String(Base64Utils.decode(((HashMap<String, String>) object).get("userId").getBytes()), StandardCharsets.UTF_8));
                    historyAuth.setHistoryAuthName(new String(Base64Utils.decode(((HashMap<String, String>) object).get("userName").getBytes()), StandardCharsets.UTF_8));
                    historyAuth.setHistoryAuthType(HistoryAuthType.REVISION);
                    responseWrapsodyDocumentDto.getAuths().add(historyAuth);
                }
            }
        }

        if (!(viewList instanceof String)) {
            viewList = json.getJSONObject("viewAuthList").get("user");
            if (viewList instanceof JSONObject) {
                HistoryAuth historyAuth = new HistoryAuth();
                historyAuth.setHistoryAuthId(new String(Base64Utils.decode(((JSONObject) viewList).getString("userId").getBytes()), "UTF-8"));
                historyAuth.setHistoryAuthName(new String(Base64Utils.decode(((JSONObject) viewList).getString("userName").getBytes()), "UTF-8"));
                historyAuth.setHistoryAuthType(HistoryAuthType.READ);
                responseWrapsodyDocumentDto.getAuths().add(historyAuth);
            } else if (viewList instanceof JSONArray) {
                List<Object> list = ((JSONArray) viewList).toList();
                for (Object object : list) {
                    HistoryAuth historyAuth = new HistoryAuth();
                    historyAuth.setHistoryAuthId(new String(Base64Utils.decode(((HashMap<String, String>) object).get("userId").getBytes()), "UTF-8"));
                    historyAuth.setHistoryAuthName(new String(Base64Utils.decode(((HashMap<String, String>) object).get("userName").getBytes()), "UTF-8"));
                    historyAuth.setHistoryAuthType(HistoryAuthType.READ);
                    responseWrapsodyDocumentDto.getAuths().add(historyAuth);
                }
            }
        }

        if (!(tagList instanceof String)) {
            tagList = json.getJSONObject("tagList").get("tagInfo");
            if (tagList instanceof JSONObject) {
                HistoryTag historyTag = new HistoryTag();
                historyTag.setHistoryTagCode(new String(Base64Utils.decode(((JSONObject) tagList).getString("tagCode").getBytes()), "UTF-8"));
                historyTag.setHistoryTagName(new String(Base64Utils.decode(((JSONObject) tagList).getString("content").getBytes()), "UTF-8"));
                if (((JSONObject) tagList).getString("tagType").equals("Y")) {
                    historyTag.setHistoryTagType(HistoryTagType.REQUIRED);
                } else if (((JSONObject) tagList).getString("tagType").equals("N")) {
                    historyTag.setHistoryTagType(HistoryTagType.SELECTION);
                } else {
                    historyTag.setHistoryTagType(HistoryTagType.CUSTOM);
                }
                responseWrapsodyDocumentDto.getTags().add(historyTag);
            } else if (tagList instanceof JSONArray) {
                List<Object> list = ((JSONArray) tagList).toList();
                for (Object object : list) {
                    HistoryTag historyTag = new HistoryTag();
                    historyTag.setHistoryTagCode(((HashMap<String, String>) object).get("tagCode"));
                    historyTag.setHistoryTagName(new String(Base64Utils.decode(((HashMap<String, String>) object).get("content").getBytes()), "UTF-8"));
                    if ((((HashMap<String, String>) object).get("tagType").equals("Y"))) {
                        historyTag.setHistoryTagType(HistoryTagType.REQUIRED);
                    } else if (((HashMap<String, String>) object).get("tagType").equals("N")) {
                        historyTag.setHistoryTagType(HistoryTagType.SELECTION);
                    } else {
                        historyTag.setHistoryTagType(HistoryTagType.CUSTOM);
                    }
                    responseWrapsodyDocumentDto.getTags().add(historyTag);
                }
            }
        }

        return responseWrapsodyDocumentDto;
    }
}
