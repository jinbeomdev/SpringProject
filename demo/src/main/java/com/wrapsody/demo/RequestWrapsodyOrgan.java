package com.wrapsody.demo;

import org.springframework.util.Base64Utils;

public class RequestWrapsodyOrgan extends RequestWrapsody {
    private final String uri = "";
    private String userId;
    private String requestMsg;

    public RequestWrapsodyOrgan(String userId) {
        this("", userId);
    }

    public RequestWrapsodyOrgan(String responseType, String userId) {
        super("GETORGAN", responseType);

        this.userId = userId;
        this.requestMsg = "<?xml version=1.0 encoding=UTF-8?>" +
                "<getorgan_request>" +
                "<uuid></uuid>" +
                "<userId>" + Base64Utils.encodeToString(userId.getBytes()) + "</userId>" +
                "</getorgan_request>";
    }

    public String getOrgan() {
        /*TODO : requestMSG을 암호화 */
        return getRestTemplateTest().postForObject(this.getServerUri() + uri, requestMsg, String.class);
    }
}
