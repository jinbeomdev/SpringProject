package com.wrapsody.demo;

import org.springframework.http.HttpEntity;

public class RequestWrapsodySetAuth extends RequestWrapsody {

    public RequestWrapsodySetAuth(String syncId,
                                  String masterId,
                                  String checkoutUserIds,
                                  String checkoutDeptCodes,
                                  Boolean viewAuthAllUsers,
                                  String viewUserIds,
                                  String viewDeptCodes) {
        super("SETAUTH");
        this.getMap().add("MSG", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<setauth_request>\n" +
                "  <ver>1.0</ver>\n" +
                "  <syncId>" + syncId + "</syncId>\n" +
                "  <master>" + masterId + "</master>\n" +
                "  <checkoutUserIds>" + checkoutUserIds + "</checkoutUserIds>\n" +
                "  <checkoutDeptCodes>" + checkoutDeptCodes +"</checkoutDeptCodes>\n" +
                "  <viewAuthAllUsers>" + viewAuthAllUsers + "</viewAuthAllUsers>\n" +
                "  <viewUserIds>" + viewUserIds + "</viewUserIds>\n" +
                "  <viewDeptCodes>" + viewDeptCodes + "</viewDeptCodes>\n" +
                "</setauth_request>");

        this.setRequest(new HttpEntity<>(getMap(), getHttpHeaders()));
        this.setResponse(this.getRestTemplate().postForEntity(URI, this.getRequest(), String.class));
    }

    String addAuths() {
        return this.getResponse().getBody();
    }
}
