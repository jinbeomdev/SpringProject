package com.wrapsody.demo;

import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@NoArgsConstructor
@Getter
@Setter
public class RequestWrapsody {
    static final String URI = "http://192.168.100.30:9090/filesync/filesync/service/proxy.do";
    static final String USER_ID = "d3JhcHNvZHluZXd1c2Vy";
    static final String PASSWORD = "MTExMQ==";

    private RestTemplate restTemplate = new RestTemplate();
    private HttpHeaders httpHeaders = new HttpHeaders();
    private MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
    private HttpEntity<MultiValueMap<String, String>> request;
    private ResponseEntity<String> response;

    public RequestWrapsody(String type) {
        map.add("MSGTYPE", "PROXY/" + type);
        map.add("VERSION", "1.0");
        map.add("LANG", "KO");
        map.add("CHARSET", "UTF-8");
        map.add("ENCTYPE", "BASE64");
    }
}