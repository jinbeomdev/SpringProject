package com.wrapsody.demo.wrapsody;

import org.springframework.http.HttpEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;

public class RequestWrapsodyOrgan extends RequestWrapsody {
    public RequestWrapsodyOrgan(String uuid, String userId) {
        super("GETORGAN");
        this.getMap().add("MSG", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<getorgan_request>\n" +
                "   <uuid>" + uuid + "</uuid>\n" +
                "   <userId>" + Base64Utils.encodeToString(userId.getBytes()) + "</userId>\n" +
                "</getorgan_request>");

        this.setRequest(new HttpEntity(getMap(), getHttpHeaders()));
        this.setResponse(this.getRestTemplate().postForEntity(URI, this.getRequest(), String.class));
    }

    public String getOrgan() {
        String json = getResponse().getBody();
        json = json.substring(json.indexOf("MSG=") + 4);

        return UriUtils.decode(json, Charset.defaultCharset());
    }
}
