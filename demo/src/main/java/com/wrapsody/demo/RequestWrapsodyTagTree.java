package com.wrapsody.demo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Base64Utils;

import java.io.IOException;

@NoArgsConstructor
@Getter
public class RequestWrapsodyTagTree extends RequestWrapsody {
    private final String uri = "/filesync/service/tagtree.do";
    private String userId;
    private String requestMsg;

    public RequestWrapsodyTagTree(String userId) {
        this(userId, "");
    }

    public RequestWrapsodyTagTree(String responseType, String userId) {
        super("TAGTREE", responseType);

        this.userId = userId;
        this.requestMsg = "<?xml version=1.0 encoding=UTF-8?>" +
                "<tagtree_request>" +
                "<ver>1.0</ver>" +
                "<uuid></uuid>" +
                "<userId>" + Base64Utils.encodeToString(userId.getBytes()) + "</userId>" +
                "</tagtree_request>";
    }

    public String getTagTree() throws IOException {
        /*TODO : requestMSG을 암호화 */
/*        XmlMapper xmlMapper  = new  XmlMapper();
        ObjectMapper objectMapper = new ObjectMapper();

        String xmlResult = getRestTemplateTest().postForObject(this.getServerUri() + uri, requestMsg, String.class);
        JsonNode jsonNode = xmlMapper.readTree(xmlResult.getBytes());

        return objectMapper.writeValueAsString(jsonNode);*/
        return null;
    }
}