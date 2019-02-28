package com.wrapsody.demo;

import lombok.*;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Getter
@Setter
public class RequestWrapsody {
    private final String serverUri = "http://192.168.100.30:9090/filesync/";

    private String msgType;
    private String version = "1.0";
    private String lang = "KO";
    private String charset = "UTF-8";
    private String encType = "BASE64";
    private String responseType;

    private RestTemplate restTemplateTest = new RestTemplate();

    RequestWrapsody(String msgType, String responseType) {
        this.msgType = msgType;
        this.responseType = responseType;
    }

    public String getRequest() {
        return "MSGTYPE:" + msgType + "\n" +
                "VERSION:" + version + "\n" +
                "LANG:" + lang + "\n" +
                "CHARSET:" + charset + "\n" +
                "ENCTYPE:" + encType + "\n" +
                "RESPONSETYPE:" + responseType + "\n";
    }
}