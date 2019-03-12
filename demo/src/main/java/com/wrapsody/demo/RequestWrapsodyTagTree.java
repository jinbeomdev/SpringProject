package com.wrapsody.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.http.HttpEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;

@NoArgsConstructor
@Getter
public class RequestWrapsodyTagTree extends RequestWrapsody {
    static private final int PRETTY_FORMAT = 4;

    public RequestWrapsodyTagTree(String uuid, String userId) {
        super("TAGTREE");
        this.getMap().add("MSG", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<tagtree_request>\n" +
                "   <ver>1.0</ver>\n" +
                "   <uuid>" + uuid + "</uuid>\n" +
                "   <userId>" + Base64Utils.encodeToString(userId.getBytes()) + "</userId>\n" +
                "</tagtree_request>");
        this.setRequest(new HttpEntity(getMap(), getHttpHeaders()));
        this.setResponse(getRestTemplate().postForEntity(URI, this.getRequest(), String.class));
    }

    public String getTagTree() {
        String xml = this.getResponse().getBody();
        xml = xml.substring(xml.indexOf("msg=") + 4);
        xml = UriUtils.decode(xml, Charset.defaultCharset());
        JSONObject json = XML.toJSONObject(xml, true);
        return json.toString(PRETTY_FORMAT);
    }
}