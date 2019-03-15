package com.wrapsody.demo;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void requestWrapsodyDocumentWithBadSyncId() throws IOException {
        String syncId = "1";
        HttpUriRequest request = new HttpGet("http://localhost:8080/wrapsody/document/syncId=" + syncId);

        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(404)
        );
    }

    @Test
    public void requestWrapsodyCreateHistoryWithBadMasterId() throws IOException {
        String syncId = "201902221730014cdeac96d86649f385ba485e972032aa";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/history?syncId=" + syncId);

        String json = "{\n" +
                "  \"historyMasterId\":\"wrapsodynewuser\",\n" +
                "  \"historyMasterName\":\"test\",\n" +
                "  \"historyPreSetName\":\"test\",\n" +
                "  \"historyViewAuthAllUsers\":\"false\",\n" +
                "  \"tags\":[\n" +
                "    {\"historyTagCode\":\"\", \"historyTagName\":\"aaa\", \"historyTagType\":\"REQUIRED\"},\n" +
                "    {\"historyTagCode\":\"\", \"historyTagName\":\"testtag1\", \"historyTagType\":\"CUSTOM\"},\n" +
                "    {\"historyTagCode\":\"\", \"historyTagName\":\"testtag2\", \"historyTagType\":\"CUSTOM\"}\n" +
                "  ],\n" +
                "  \"auths\":[\n" +
                "    {\"historyAuthId\":\"lbj2\", \"historyAuthName\":\"test2\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"syong\", \"historyAuthName\":\"test3\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"genie\", \"historyAuthName\":\"test4\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"minji.\", \"historyAuthName\":\"test5\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"minji..\", \"historyAuthName\":\"test6\", \"historyAuthType\":\"REVISION\"},\n" +
                "    {\"historyAuthId\":\"jinbeom\", \"historyAuthName\":\"test7\", \"historyAuthType\":\"READ\"}\n" +
                "  ]\n" +
                "\n" +
                "}";

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
        client.close();
    }
}
