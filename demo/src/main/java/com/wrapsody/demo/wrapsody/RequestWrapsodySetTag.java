package com.wrapsody.demo.wrapsody;

import com.wrapsody.demo.exception.WrapsodyNotFoundException;
import com.wrapsody.demo.exception.WrapsodyUnauthorizedException;
import org.springframework.http.HttpEntity;
import org.springframework.util.Base64Utils;

public class RequestWrapsodySetTag extends RequestWrapsody {
    public RequestWrapsodySetTag(String syncId, String tags) {
        super("TAG");
        this.getMap().add("MSG", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<tag_request>\n" +
                "  <ver>1.0</ver>\n" +
                "  <user>\n" +
                "    <userId>" + USER_ID + "</userId>\n" +
                "    <password>" + PASSWORD + "</password>\n" +
                "  </user>\n" +
                "  <syncId>" + syncId + "</syncId>\n" +
                "<tags>" + Base64Utils.encodeToString(tags.getBytes()) + "</tags>" +
                "<tagCodes></tagCodes>" +
                "</tag_request>");
        this.setRequest(new HttpEntity(this.getMap(), this.getHttpHeaders()));
        this.setResponse(this.getRestTemplate().postForEntity(URI, this.getRequest(), String.class));
    }

    public String addTags() throws WrapsodyUnauthorizedException,
            WrapsodyNotFoundException {
        if(!statusIsSuccess()) {
            String failMsg = getFailMsg();
            if (failMsg.contains("PERMISSION_WRONG") ||
                failMsg.contains("INVALID_MASTERID")) {
                throw new WrapsodyUnauthorizedException(failMsg);
            }
            throw new WrapsodyNotFoundException(failMsg);
        }
        return getMsg();
    }
}
