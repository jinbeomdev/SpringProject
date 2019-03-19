package com.wrapsody.demo.wrapsody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.io.StringReader;

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

    public boolean statusIsSuccess() {
        int start = response.getBody().indexOf("STATUS=") + 7;
        int end = response.getBody().indexOf("&MSG=");

        if (response.getBody().substring(start, end).equals("SUCCESS")) {
            return true;
        }

        return false;
    }

    public String getMsg() {
        int start = response.getBody().indexOf("MSG=") + 4;
        return UriUtils.decode(response.getBody().substring(start), "UTF-8");
    }

    public String getFailMsg() {
        String msg = "";

        SAXBuilder saxBuilder = new SAXBuilder();
        try {
            Document document = saxBuilder.build(new StringReader(getMsg()));
            Element root = document.getRootElement();
            msg = root.getChildText("MESSAGE");
        } catch (JDOMException | IOException ex) {
            ex.printStackTrace();
        }

        return msg;
    }
}